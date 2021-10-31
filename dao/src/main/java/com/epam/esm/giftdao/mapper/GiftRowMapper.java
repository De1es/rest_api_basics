package com.epam.esm.giftdao.mapper;

import com.epam.esm.gift.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class GiftRowMapper implements RowMapper<GiftCertificate> {

  @Override
  public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
    GiftCertificate giftCertificate = new GiftCertificate();

    giftCertificate.setId(rs.getLong("id"));
    giftCertificate.setName(rs.getString("name"));
    giftCertificate.setDescription(rs.getString("description"));
    giftCertificate.setPrice(rs.getInt("price"));
    giftCertificate.setDuration(rs.getInt("duration"));
    giftCertificate.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
    giftCertificate.setLastUpdateDate(rs.getTimestamp("last_update_date").toLocalDateTime());

    return giftCertificate;
  }
}