package com.epam.esm.tagdao;

import com.epam.esm.tag.Tag;
import com.epam.esm.tagdao.mapper.TagRowMapper;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
@Data
public class TagDaoImpl implements TagDao {

  private final String READ_BY_ID_QUERY = "SELECT * FROM tag WHERE id = ?";
  private final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM tag";
  private final String DELETE_QUERY = "DELETE FROM tag where id = ?";

  final JdbcTemplate jdbcTemplate;
  final TagRowMapper tagRowMapper;


  @Override
  public Tag create(Tag tag) {
    SimpleJdbcInsert jdbcInsert =
        new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("tag")
            .usingGeneratedKeyColumns("id");
    Map<String, Object> params = new HashMap<>();
    params.put("id", tag.getId());
    params.put("name", tag.getName());
    Long id = jdbcInsert.executeAndReturnKey(params).longValue();
    tag.setId(id);
    return tag;
  }

  @Override
  public Tag readById(Long id) {
    return jdbcTemplate.queryForObject(READ_BY_ID_QUERY, tagRowMapper, id);
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
}
