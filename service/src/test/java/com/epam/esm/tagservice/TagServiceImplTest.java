package com.epam.esm.tagservice;

import com.epam.esm.tag.Tag;
import com.epam.esm.tagdao.TagDao;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Data
class TagServiceImplTest {

  @Mock
  TagDao tagDaoMock;

  @InjectMocks
  TagServiceImpl tagService;

  static Tag testTagWithoutId;
  static Tag testTagWithId;

  @BeforeAll
  static void beforeAll() {
    testTagWithoutId = new Tag("TestTag1");
    testTagWithId = new Tag(10L, "TestTag2");
  }

  @Test
  void save() {
    when(tagDaoMock.create(testTagWithoutId)).thenReturn(testTagWithId);
    assertEquals(testTagWithId, tagService.save(testTagWithoutId));
    verify(tagDaoMock).create(testTagWithoutId);
  }

  @Test
  void readById() {
    when(tagDaoMock.readById(1L)).thenReturn(testTagWithId);
    assertEquals(testTagWithId, tagService.readById(1L));
    verify(tagDaoMock).readById(1L);
  }

  @Test
  void delete() {
    when(tagDaoMock.delete(testTagWithId.getId())).thenReturn(10L);
    assertEquals(10L, tagService.delete(testTagWithId.getId()));
    verify(tagDaoMock).delete(testTagWithId.getId());

  }
}