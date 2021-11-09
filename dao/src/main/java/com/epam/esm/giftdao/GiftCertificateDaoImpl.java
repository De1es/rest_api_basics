package com.epam.esm.giftdao;

import com.epam.esm.exception.CustomDaoException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.mapper.GiftRowMapper;
import com.epam.esm.giftdao.mapper.GiftWithTagsExtractor;
import lombok.Data;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
@Data
public class GiftCertificateDaoImpl implements GiftCertificateDao {

  private final String READ_BY_ID_QUERY = "SELECT * FROM public.gift_certificate WHERE id = ?";
  private final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM gift_certificate";
  private final String DELETE_QUERY = "DELETE FROM gift_certificate where id = ?";
  private final String LIST_BY_TAG_NAME_QUERY =
      "SELECT g.id, g.name, g.description, g.price, g.duration, g.create_date, g.last_update_date, t.id as t_id," +
          " t.name as t_name\n" +
          "\tFROM public.gift_certificate g \n" +
          "\tLEFT JOIN public.gift_certificate_tag gt on (g.id = gt.gift_certificate_id)\n" +
          "\tLEFT JOIN public.tag t on (gt.tag_id = t.id)\n";

  final JdbcTemplate jdbcTemplate;
  final GiftRowMapper giftRowMapper;
  final GiftWithTagsExtractor giftWithTagsExtractor;


  @Override
  public GiftCertificate create(GiftCertificate gift) {

    SimpleJdbcInsert jdbcInsert =
        new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("gift_certificate")
            .usingGeneratedKeyColumns("id");
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", gift.getId());
      params.put("name", gift.getName());
      params.put("description", gift.getDescription());
      params.put("price", gift.getPrice());
      params.put("duration", gift.getDuration());
      params.put("create_date", LocalDateTime.now());
      params.put("last_update_date", LocalDateTime.now());
      Long id = jdbcInsert.executeAndReturnKey(params).longValue();
      gift.setId(id);
      return gift;
    } catch (DataAccessException e) {
      throw new CustomDaoException("Creating GiftCertificate exception", e);
    }
  }

  @Override
  public GiftCertificate readById(Long id) {
    try {
      return jdbcTemplate.queryForObject(READ_BY_ID_QUERY, giftRowMapper, id);
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new ResourceNotFoundException("Requested GiftCertificate not found (id = " + id + ")", e);
    } catch (DataAccessException e) {
      throw new ResourceNotFoundException("Reading by id GiftCertificate exception", e);
    }
  }


  @Override
  public List<GiftCertificate> list(String tagName, String partOfName, int limit, String sortBy, String sortOrder) {
    StringBuilder query = new StringBuilder(LIST_BY_TAG_NAME_QUERY);
    MapSqlParameterSource params = new MapSqlParameterSource();
    StringJoiner stringJoiner = new StringJoiner(" AND ", "WHERE ", "");
    if (tagName != null && !tagName.equals("")) {
      stringJoiner.add("t.name = :tagName");
      params.addValue("tagName", tagName);
    }
    if (partOfName != null && !partOfName.equals("")) {
      String name = "%" + partOfName + "%";
      stringJoiner.add("g.name LIKE :partOfName");
      params.addValue("partOfName", name);
    }
    if (tagName != null || partOfName != null) {
      query.append(stringJoiner);
    }
    if (sortBy != null) {
      query.append(" ORDER BY g.");
      query.append(sortBy);
      if (sortOrder != null) {
        query.append(" ");
        query.append(sortOrder);
      }
    }
    if (limit != 0) {
      query.append(" LIMIT :limit");
      params.addValue("limit", limit);
    }
    NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    try {
      return namedJdbcTemplate.query(query.toString(), params, giftWithTagsExtractor);
    } catch (DataAccessException e) {
      throw new CustomDaoException("Getting list of Gifts exception (you can sorting by 'name' or 'create_date' or " +
          "'last_update_date' in 'ASC' or 'DESC' sorting order)", e);
    }
  }


  @Override
  public int update(GiftCertificate gift) {
    StringBuilder update = new StringBuilder("UPDATE gift_certificate SET");
    MapSqlParameterSource params = new MapSqlParameterSource();
    if (gift.getName() != null) {
      update.append(" name = :name,");
      params.addValue("name", gift.getName());
    }
    if (gift.getDescription() != null) {
      update.append(" description = :description,");
      params.addValue("description", gift.getDescription());
    }
    if (gift.getPrice() != 0) {
      update.append(" price = :price,");
      params.addValue("price", gift.getPrice());
    }
    if (gift.getDuration() != 0) {
      update.append(" duration = :duration,");
      params.addValue("duration", gift.getDuration());
    }
    if (!params.getValues().isEmpty()) {
      update.append(" last_update_date = :last_update_date WHERE id = :id");
      params.addValue("last_update_date", LocalDateTime.now());
      params.addValue("id", gift.getId());
      NamedParameterJdbcTemplate namedParam = new NamedParameterJdbcTemplate(jdbcTemplate);
      try {
        return namedParam.update(update.toString(), params);
      } catch (DataAccessException e) {
        throw new CustomDaoException("Updating GiftCertificate exception", e);
      }
    }
    return 0;
  }

  @Override
  public Long delete(Long id) {
    try {
      jdbcTemplate.update(DELETE_QUERY, id);
    } catch (DataAccessException e) {
      throw new CustomDaoException("Deleting GiftCertificate exception", e);
    }
    return id;
  }

  @Override
  public int countAll() {
    Integer count;
    try {
      count = jdbcTemplate.queryForObject(COUNT_ALL_QUERY, Integer.class);
    } catch (DataAccessException e) {
      throw new CustomDaoException("Getting count of all gifts exception", e);
    }
    if (count != null) {
      return count;
    }
    return -1;
  }

}
