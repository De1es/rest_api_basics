package com.epam.esm.tagdao;

import com.epam.esm.tag.Tag;
import com.epam.esm.tagdao.mapper.TagRowMapper;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Data
public class TagDaoImpl implements TagDao {

  private final String CREATE_TAG_QUERY =
      "INSERT INTO public.tag(\n" +
          "\tname)\n" +
          "\tSELECT ?\n" +
          "\tWHERE\n" +
          "\tNOT EXISTS(\n" +
          "\tSELECT name FROM tag WHERE name = ?\n" +
          "\t);";
  private final String READ_BY_ID_QUERY = "SELECT * FROM tag WHERE id = ?";
  private final String READ_BY_NAME_QUERY = "SELECT * FROM tag WHERE name = ?";
  private final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM tag";
  private final String DELETE_QUERY = "DELETE FROM tag where id = ?";
  private final String SELECT_TAGS_FOR_GIFT_QUERY =
      "SELECT t.id, t.name\tFROM public.tag t \n" +
          "\tJOIN public.gift_certificate_tag gt on (t.id = gt.tag_id)\n" +
          "\tWHERE gift_certificate_id = ?";

  final JdbcTemplate jdbcTemplate;
  final TagRowMapper tagRowMapper;


  @Override
  public Tag create(Tag tag) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(CREATE_TAG_QUERY, new String[]{"id"});
        ps.setString(1, tag.getName());
        ps.setString(2, tag.getName());
        return ps;
      }
    }, keyHolder);
    if (keyHolder.getKey() == null) {
      return null;
    }
    tag.setId(keyHolder.getKey().longValue());
    return tag;
  }

  @Override
  public Tag readById(Long id) {
    return jdbcTemplate.queryForObject(READ_BY_ID_QUERY, tagRowMapper, id);
  }

  @Override
  public Tag readByName(String tagName) {
    return jdbcTemplate.queryForObject(READ_BY_NAME_QUERY, tagRowMapper, tagName);
  }

  @Override
  public Long delete(Long id) {
    jdbcTemplate.update(DELETE_QUERY, id);
    return id;
  }

  @Override
  public int countAll() {
    Integer count = jdbcTemplate.queryForObject(COUNT_ALL_QUERY, Integer.class);
    if (count != null) {
      return count;
    }
    return -1;
  }

  @Override
  public List<Tag> readTagsForGift(Long giftId) {
    return jdbcTemplate.query(SELECT_TAGS_FOR_GIFT_QUERY, tagRowMapper, giftId);
  }
}
