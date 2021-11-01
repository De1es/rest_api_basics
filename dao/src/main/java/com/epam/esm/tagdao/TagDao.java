package com.epam.esm.tagdao;

import com.epam.esm.tag.Tag;


public interface TagDao {

  Tag create (Tag tag);
  Tag readById (Long id);
  Long delete (Long id);
  int countAll();
}
