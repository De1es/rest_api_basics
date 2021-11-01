package com.epam.esm.tagdao;

import com.epam.esm.config.DaoDevConfig;
import com.epam.esm.tag.Tag;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = DaoDevConfig.class)
@Data
class TagDaoImplTest {

  @Autowired
  TagDao tagDao;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  void create() {
    Tag testTag = new Tag("TestTag1");
    Tag createdTag = tagDao.create(testTag);
    testTag.setId(createdTag.getId());
    assertEquals(testTag, createdTag);
  }

  @Test
  void readById() {
    Tag testTag = new Tag("TestTag2");
    Tag createdTag = tagDao.create(testTag);
    Tag readedTag = tagDao.readById(createdTag.getId());
    assertEquals(createdTag, readedTag);
  }

  @Test
  void delete() {
    Integer countBefore = tagDao.countAll();
    tagDao.delete(1L);
    Integer countAfter = tagDao.countAll();
    assertEquals(countBefore-countAfter, 1);
  }
}