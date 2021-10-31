package com.epam.esm.giftdao.impl;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.GiftCertificateDao;
import com.epam.esm.giftdao.config.DaoDevConfig;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = DaoDevConfig.class)
@Data
class GiftCertificateDaoImplTest {

  @Autowired
  GiftCertificateDao giftCertificateDao;

  @Autowired
  JdbcTemplate jdbcTemplate;

  static GiftCertificate testGift;

  @BeforeAll
  static void beforeAll() {
    testGift = new GiftCertificate("Gift", "Description", 1, 10,
        LocalDateTime.of(2020, 12, 15, 10, 10),
        LocalDateTime.of(2020, 6, 7, 20, 20));
  }


  @Test
  @Transactional
  void create() {
    int countBeforeCreate = giftCertificateDao.countAll();
    giftCertificateDao.create(testGift);
    int  countAfterCreate = giftCertificateDao.countAll();
    assertEquals(countAfterCreate - countBeforeCreate, 1);
  }

  @Test
  @Transactional
  void readById() {
    GiftCertificate giftCertificate = giftCertificateDao.create(testGift);
    GiftCertificate gift = giftCertificateDao.readById(giftCertificate.getId());
    giftCertificate.setCreateDate(gift.getCreateDate());
    giftCertificate.setLastUpdateDate(gift.getLastUpdateDate());
    assertEquals(gift, giftCertificate);
  }

  @Test
  @Transactional
  void readAll() {
    List<GiftCertificate> gifts = giftCertificateDao.readAll();
    int countOfAll = giftCertificateDao.countAll();
    assertEquals(countOfAll, gifts.size());
  }

  @Test
  @Transactional
  void update() {
    GiftCertificate gift = giftCertificateDao.readById(2L);
    gift.setName("New Name");
    gift.setDescription("New Description");
    giftCertificateDao.update(gift);
    GiftCertificate gift2 = giftCertificateDao.readById(2L);
    gift.setLastUpdateDate(gift2.getLastUpdateDate());
    assertEquals(gift, gift2);
  }


  @Test
  @Transactional
  void delete() {
    int countBeforeCreate = giftCertificateDao.countAll();
    giftCertificateDao.delete(1L);
    int  countAfterCreate = giftCertificateDao.countAll();
    assertEquals(countBeforeCreate - countAfterCreate, 1);

  }
}