package com.epam.esm.tagservice;

import com.epam.esm.tag.Tag;

/**
 * The TagService interface
 */
public interface TagService {

  /**
   * Create new Tag
   *
   * @param tag {@link Tag}
   * @return {@link Tag}
   */
  Tag save(Tag tag);

  /**
   * Find Tag by id
   *
   * @param id Tag id
   * @return {@link Tag}
   */
  Tag readById(Long id);

  /**
   * Delete Tag by id
   *
   * @param id Tag id
   * @return {@link Long}
   */
  Long delete(Long id);
}
