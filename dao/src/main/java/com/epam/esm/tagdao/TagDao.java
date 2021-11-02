package com.epam.esm.tagdao;

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
}
