package com.epam.esm.giftservice;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.GiftCertificateDao;
import com.epam.esm.tagdao.TagDao;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Data
class GiftCertificateServiceImplTest {

  @Mock
  GiftCertificateDao giftDaoMock;

  @Mock
  TagDao tagDaoMock;

  @InjectMocks
  GiftCertificateServiceImpl giftCertificateService;

  static GiftCertificate testGiftWithoutId;
  static GiftCertificate testGiftWithId;
  static List<GiftCertificate> gifts;

  @BeforeAll
  static void beforeAll() {
    testGiftWithoutId = new GiftCertificate("Gift", "Description", 1, 10,
        LocalDateTime.of(2020, 12, 15, 10, 10),
        LocalDateTime.of(2020, 6, 7, 20, 20));
    testGiftWithId = new GiftCertificate(1L, "Gift", "Description", 1, 10,
        LocalDateTime.of(2020, 12, 15, 10, 10),
        LocalDateTime.of(2020, 6, 7, 20, 20), new LinkedList<>());
    gifts = new ArrayList<>();
    gifts.add(testGiftWithId);
  }


  @Test
  void save() {
    when(giftDaoMock.create(testGiftWithoutId)).thenReturn(testGiftWithId);
    when(tagDaoMock.updateTagsForGift(testGiftWithId)).thenReturn(testGiftWithId);
    assertEquals(testGiftWithId, giftCertificateService.save(testGiftWithoutId));
    verify(giftDaoMock).create(testGiftWithoutId);
  }

  @Test
  void readById() {
    when(giftDaoMock.readById(1L)).thenReturn(testGiftWithId);
    assertEquals(testGiftWithId, giftCertificateService.readById(1L));
    verify(giftDaoMock).readById(1L);
  }

  @Test
  void list() {
    when(giftDaoMock.list("tagName", "partOfName", 5, "name", "DESC"))
        .thenReturn(new LinkedList<>());
    assertEquals(new LinkedList<>(),
        giftCertificateService.list("tagName", "partOfName", 5, "name", "DESC"));
    verify(giftDaoMock).list("tagName", "partOfName", 5, "name", "DESC");
  }

  @Test
  void update() {
    when(giftDaoMock.update(testGiftWithoutId)).thenReturn(1);
    when(tagDaoMock.updateTagsForGift(testGiftWithoutId)).thenReturn(testGiftWithoutId);
    assertEquals(giftCertificateService.update(testGiftWithoutId), testGiftWithoutId);
    verify(giftDaoMock).update(testGiftWithoutId);
    verify(tagDaoMock).updateTagsForGift(testGiftWithoutId);
  }

  @Test
  void delete() {
    when(giftDaoMock.delete(testGiftWithId.getId())).thenReturn(1L);
    assertEquals(1L, giftCertificateService.delete(1L));
    verify(giftDaoMock).delete(testGiftWithId.getId());
  }


}