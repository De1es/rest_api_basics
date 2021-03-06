package com.epam.esm.tagdao;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.tag.Tag;

import java.util.List;

/**
 * The TagDao interface
 */
public interface TagDao {

  /**
   * Create new Tag
   *
   * @param tag {@link Tag}
   * @return {@link Tag}
   */
  Tag create(Tag tag);

  /**
   * Find Tag by id
   *
   * @param id Tag id
   * @return {@link Tag}
   */
  Tag readById(Long id);

  /**
   * Find Tag by name
   *
   * @param tagName Tag name
   * @return {@link Tag}
   */
  Tag readByName(String tagName);

  /**
   * Delete Tag by id
   *
   * @param id Tag id
   * @return {@link Long}
   */
  Long delete(Long id);

  /**
   * Return count off all Tags
   *
   * @return count of {@link Tag}
   */
  int countAll();

  /**
   * Find Tags of GiftCertificate
   *
   * @param giftId GiftCertificate id
   * @return list of {@link Tag}
   */
  List<Tag> readTagsForGift(Long giftId);

  /**
   * Delete Tag from GiftCertificate by Tag name
   *
   * @param GiftId  GiftCertificate id
   * @param tagName Tag name
   */
  void deleteTagFromGiftCertificateByName(Long GiftId, String tagName);

  /**
   * Add tag to GiftCertificate by Tag name
   *
   * @param GiftId  GiftCertificate id
   * @param tagName Tag name
   */
  void addTagToGiftCertificateByName(Long GiftId, String tagName);

  /**
   * Update GiftCertificate's Tags
   *
   * @param giftCertificate {@link GiftCertificate}
   * @return {@link GiftCertificate}
   */
  GiftCertificate updateTagsForGift(GiftCertificate giftCertificate);
}
