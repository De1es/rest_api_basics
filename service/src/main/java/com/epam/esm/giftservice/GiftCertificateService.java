package com.epam.esm.giftservice;

import com.epam.esm.gift.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {
  GiftCertificate save (GiftCertificate gift);
  GiftCertificate readById(Long id);
  List<GiftCertificate> readAll();
  int update (GiftCertificate gift);
  Long delete (Long id);

}
