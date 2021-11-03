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

  /**
   * Find list of GiftCertificates
   *
   * @param tagName    Tag name
   * @param partOfName part of GiftCertificate name
   * @param limit      count of read items
   * @param sortBy     sort by name/last_update_date/create_date
   * @param sortOrder  sort order ASC/DESC
   * @return list of {@link GiftCertificate}
   */
  List<GiftCertificate> list(String tagName, String partOfName, int limit, String sortBy, String sortOrder);

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
