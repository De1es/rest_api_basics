package com.epam.esm.tagdao;

import com.epam.esm.exception.CustomDaoException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.tag.Tag;
import com.epam.esm.tagdao.mapper.TagRowMapper;
import lombok.Data;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
  private final String DELETE_TAG_FOR_GIFT = "DELETE FROM public.gift_certificate_tag\n" +
      "\tWHERE gift_certificate_id = ? AND tag_id = (SELECT id FROM tag WHERE name = ?)";
  private final String INSERT_INTO_GIFT_CERTIFICATE_TAG = "INSERT INTO public.gift_certificate_tag(\n" +
      "\tgift_certificate_id, tag_id)\n" +
      "\tVALUES (?, (SELECT id FROM tag WHERE name = ?))";

  final JdbcTemplate jdbcTemplate;
  final TagRowMapper tagRowMapper;


  @Override
  public Tag create(Tag tag) {
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(con -> {
        PreparedStatement ps = con.prepareStatement(CREATE_TAG_QUERY, new String[]{"id"});
        ps.setString(1, tag.getName());
        ps.setString(2, tag.getName());
        return ps;
      }, keyHolder);
      if (keyHolder.getKey() == null) {
        return null;
      }
      tag.setId(keyHolder.getKey().longValue());
      return tag;
    } catch (DataAccessException e) {
      throw new CustomDaoException("Creating Tag exception", e);
    }
  }

  @Override
  public Tag readById(Long id) {
    try {
      return jdbcTemplate.queryForObject(READ_BY_ID_QUERY, tagRowMapper, id);
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new ResourceNotFoundException("Requested Tag not found (id = " + id + ")", e);
    } catch (DataAccessException e) {
      throw new CustomDaoException("Reading Tag exception", e);
    }
  }

  @Override
  public Tag readByName(String tagName) {
    try {
      return jdbcTemplate.queryForObject(READ_BY_NAME_QUERY, tagRowMapper, tagName);
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new ResourceNotFoundException("Requested Tag not found (name = " + tagName + ")", e);
    } catch (DataAccessException e) {
      throw new CustomDaoException("Reading Tag exception", e);
    }
  }

  @Override
  public Long delete(Long id) {
    try {
      jdbcTemplate.update(DELETE_QUERY, id);
    } catch (DataAccessException e) {
      throw new CustomDaoException("Deleting Tag exception", e);
    }
    return id;
  }

  @Override
  public int countAll() {
    try {
      Integer count = jdbcTemplate.queryForObject(COUNT_ALL_QUERY, Integer.class);
      if (count != null) {
        return count;
      }
      return -1;
    } catch (DataAccessException e) {
      throw new CustomDaoException("Getting count of all tags exception", e);
    }
  }

  @Override
  public List<Tag> readTagsForGift(Long giftId) {
    try {
      return jdbcTemplate.query(SELECT_TAGS_FOR_GIFT_QUERY, tagRowMapper, giftId);
    } catch (DataAccessException e) {
      throw new ResourceNotFoundException("Requested Tags for gift not found (gift's id = \" + id + \")", e);
    }
  }

  @Override
  public void deleteTagFromGiftCertificateByName(Long GiftId, String tagName) {
    try {
      jdbcTemplate.update(DELETE_TAG_FOR_GIFT, GiftId, tagName);
    } catch (DataAccessException e) {
      throw new CustomDaoException("Deleting Tags from GiftCertificate exception", e);
    }
  }

  @Override
  public void addTagToGiftCertificateByName(Long giftId, String tagName) {
    try {
      jdbcTemplate.update(INSERT_INTO_GIFT_CERTIFICATE_TAG, giftId, tagName);
    } catch (DataAccessException e) {
      throw new CustomDaoException("Adding Tag to GiftCertificate exception", e);
    }
  }

  @Override
  @Transactional
  public GiftCertificate updateTagsForGift(GiftCertificate gift) {
    try {
      List<String> newTagsNames = gift.getTags()
          .stream()
          .map(Tag::getName)
          .collect(Collectors.toList());

      List<String> oldTagsNames = readTagsForGift(gift.getId())
          .stream()
          .map(Tag::getName)
          .collect(Collectors.toList());

      oldTagsNames.stream()
          .filter(Predicate.not(newTagsNames::contains))
          .forEach((name) -> deleteTagFromGiftCertificateByName(gift.getId(), name));

      newTagsNames.stream()
          .filter(Predicate.not(oldTagsNames::contains))
          .forEach((name) -> create(new Tag(name)));
      newTagsNames.stream()
          .filter(Predicate.not(oldTagsNames::contains))
          .map(this::readByName)
          .forEach((tag) -> addTagToGiftCertificateByName(gift.getId(), tag.getName()));

      gift.setTags(readTagsForGift(gift.getId()));
      return gift;
    } catch (DataAccessException e) {
      throw new CustomDaoException("Updating GiftCertificate's Tags exception", e);
    }
  }
}
