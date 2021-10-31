package com.epam.esm.impl;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.GiftCertificateDao;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Data
class GiftCertificateServiceImplTest {

  @Mock
  GiftCertificateDao daoMock;

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
    testGiftWithId = new GiftCertificate(1L,"Gift", "Description", 1, 10,
        LocalDateTime.of(2020, 12, 15, 10, 10),
        LocalDateTime.of(2020, 6, 7, 20, 20));
    gifts = new ArrayList<>();
    gifts.add(testGiftWithId);
  }


  @Test
  void save() {
    when(daoMock.create(testGiftWithoutId)).thenReturn(testGiftWithId);
    assertEquals(testGiftWithId, giftCertificateService.save(testGiftWithoutId));
    verify(daoMock).create(testGiftWithoutId);
  }

  @Test
  void readById() {
    when(daoMock.readById(1L)).thenReturn(testGiftWithId);
    assertEquals(testGiftWithId, giftCertificateService.readById(1L));
    verify(daoMock).readById(1L);
  }

  @Test
  void readAll() {
    when(daoMock.readAll()).thenReturn(gifts);
    assertEquals(gifts, giftCertificateService.readAll());
    verify(daoMock).readAll();
  }

  @Test
  void update() {
    when(daoMock.update(testGiftWithoutId)).thenReturn(1);
    assertEquals(giftCertificateService.update(testGiftWithoutId), 1);
    verify(daoMock).update(testGiftWithoutId);
  }

  @Test
  void delete() {
    when(daoMock.delete(testGiftWithId.getId())).thenReturn(1L);
    assertEquals(1L, giftCertificateService.delete(1L));
    verify(daoMock).delete(testGiftWithId.getId());
  }
}