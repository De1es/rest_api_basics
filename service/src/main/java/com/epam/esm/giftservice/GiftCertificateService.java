package com.epam.esm.giftservice;

import com.epam.esm.gift.GiftCertificate;

import java.util.List;

/**
 * The GiftCertificateService interface
 */
public interface GiftCertificateService {

  /**
   * Create new GiftCertificate
   *
   * @param gift {@link GiftCertificate}
   * @return {@link GiftCertificate}
   */
  GiftCertificate save(GiftCertificate gift);

  /**
   * Find GiftCertificate by id
   *
   * @param id GiftCertificate id
   * @return {@link GiftCertificate}
   */
  GiftCertificate readById(Long id);


  List<GiftCertificate> readAll();

  /**
   * Update GiftCertificate
   *
   * @param gift {@link GiftCertificate}
   * @return {@link GiftCertificate}
   */
  GiftCertificate update(GiftCertificate gift);

  /**
   * Delete GiftCertificate by id
   *
   * @param id GiftCertificate id
   * @return {@link Long}
   */
  Long delete(Long id);

}
