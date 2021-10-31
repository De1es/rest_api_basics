package com.epam.esm.giftdao.impl;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.GiftCertificateDao;
import com.epam.esm.giftdao.mapper.GiftRowMapper;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Data
public class GiftCertificateDaoImpl implements GiftCertificateDao {

  private final String READ_BY_ID_QUERY = "SELECT * FROM public.gift_certificate WHERE id = ?";
  private final String READ_ALL_QUERY = "SELECT * FROM public.gift_certificate";
  private final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM gift_certificate";
  private final String DELETE_QUERY = "DELETE FROM gift_certificate where id = ?";

  final JdbcTemplate jdbcTemplate;
  final GiftRowMapper giftRowMapper;


  @Override
  public GiftCertificate create(GiftCertificate gift) {
    SimpleJdbcInsert jdbcInsert =
        new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("gift_certificate")
            .usingGeneratedKeyColumns("id");
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
  }

  @Override
  public GiftCertificate readById(Long id) {
    return jdbcTemplate.queryForObject(READ_BY_ID_QUERY, giftRowMapper, id);
  }

  @Override
  public List<GiftCertificate> readAll() {
    return jdbcTemplate.query(READ_ALL_QUERY, giftRowMapper);
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
      return namedParam.update(update.toString(), params);
    }
    return 0;
  }

  @Override
  public Long delete(Long id) {
    jdbcTemplate.update(DELETE_QUERY, id);
    return id;
  }

  public int countAll() {
    Integer count = jdbcTemplate.queryForObject(COUNT_ALL_QUERY, Integer.class);
    if (count != null) {
      return count;
    }
    return -1;
  }
}
