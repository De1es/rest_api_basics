package com.epam.esm.giftdao.mapper;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.tag.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class GiftWithTagsExtractor implements ResultSetExtractor<List<GiftCertificate>> {
  @Override
  public List<GiftCertificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
    Map<Long, GiftCertificate> gifts = new HashMap<>();
    while (rs.next()){
      GiftCertificate giftCertificate = new GiftCertificate();
      Tag tag = new Tag();
      Long giftId = rs.getLong("id");
      if (gifts.containsKey(giftId)){
        giftCertificate = gifts.get(giftId);
      } else {
        giftCertificate.setId(giftId);
        giftCertificate.setName(rs.getString("name"));
        giftCertificate.setDescription(rs.getString("description"));
        giftCertificate.setPrice(rs.getInt("price"));
        giftCertificate.setDuration(rs.getInt("duration"));
        giftCertificate.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        giftCertificate.setLastUpdateDate(rs.getTimestamp("last_update_date").toLocalDateTime());
      }

      tag.setId(rs.getLong("t_id"));
      tag.setName(rs.getString("t_name"));

      giftCertificate.addTag(tag);
      gifts.put(giftId, giftCertificate);
    }
    return new LinkedList<>(gifts.values());
  }
}
