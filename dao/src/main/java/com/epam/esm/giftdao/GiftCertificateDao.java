package com.epam.esm.giftdao;

import com.epam.esm.gift.GiftCertificate;

import java.util.List;

/**
 * The GiftCertificateDao interface
 */
public interface GiftCertificateDao {

  /**
   * Create new GiftCertificate
   *
   * @param gift {@link GiftCertificate}
   * @return {@link GiftCertificate}
   */
  GiftCertificate create(GiftCertificate gift);

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
   * @return count of updated rows
   */
  int update(GiftCertificate gift);

  /**
   * Delete GiftCertificate by id
   *
   * @param id GiftCertificate id
   * @return {@link Long}
   */
  Long delete(Long id);

  /**
   * Return count of all GiftCertificate
   *
   * @return count of GiftCertificate
   */
  int countAll();

  /**
   * Add Tag to GiftCertificate
   *
   * @param giftId GiftCertificate id
   * @param tagId  Tag id
   */
  void addTagToGift(Long giftId, Long tagId);
}
