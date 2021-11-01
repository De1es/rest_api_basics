package com.epam.esm.tagservice;

import com.epam.esm.tag.Tag;


public interface TagService {

  Tag save(Tag tag);

  Tag readById(Long id);

  Long delete(Long id);
}
