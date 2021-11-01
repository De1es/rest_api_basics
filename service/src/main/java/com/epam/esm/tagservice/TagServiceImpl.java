package com.epam.esm.tagservice;

import com.epam.esm.tag.Tag;
import com.epam.esm.tagdao.TagDao;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class TagServiceImpl implements TagService {

  private final TagDao tagDao;

  @Override
  public Tag save(Tag tag) {
    return tagDao.create(tag);
  }

  @Override
  public Tag readById(Long id) {
    return tagDao.readById(id);
  }

  @Override
  public Long delete(Long id) {
    return tagDao.delete(id);
  }
}
