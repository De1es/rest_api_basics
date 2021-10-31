package com.epam.esm.giftdao;

import com.epam.esm.gift.GiftCertificate;

import java.util.List;

public interface GiftCertificateDao {

  GiftCertificate create (GiftCertificate gift);
  GiftCertificate readById (Long id);
  List <GiftCertificate> readAll ();
  int update (GiftCertificate gift);
  Long delete (Long id);
  int countAll();
}
