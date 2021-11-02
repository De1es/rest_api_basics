package com.epam.esm.tagdao;

import com.epam.esm.tag.Tag;

import java.util.List;


public interface TagDao {

  Tag create (Tag tag);
  Tag readById (Long id);
  Tag readByName (String tagName);
  Long delete (Long id);
  int countAll();
  List<Tag> readTagsForGift (Long giftId);
}
