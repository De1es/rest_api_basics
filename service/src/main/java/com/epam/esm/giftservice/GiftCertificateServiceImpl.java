package com.epam.esm.giftservice;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.GiftCertificateDao;
import com.epam.esm.tag.Tag;
import com.epam.esm.tagdao.TagDao;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
public class GiftCertificateServiceImpl implements GiftCertificateService {

  private final GiftCertificateDao giftDao;
  private final TagDao tagDao;

  @Override
  @Transactional
  public GiftCertificate save(GiftCertificate gift) {
    GiftCertificate newGift = getGiftDao().create(gift);
    for (Tag tag : gift.getTags()) {
      Tag newTag = tagDao.create(tag);
      if (newTag == null) {
        newTag = tagDao.readByName(tag.getName());
      }
      giftDao.addTagToGift(newGift.getId(), newTag.getId());
    }
    newGift.setTags(tagDao.readTagsForGift(newGift.getId()));
    return newGift;
  }

  @Override
  @Transactional
  public GiftCertificate readById(Long id) {
    GiftCertificate gift = giftDao.readById(id);
    List <Tag> tags = tagDao.readTagsForGift(id);
    gift.setTags(tags);
    return gift;
  }

  @Override
  public List<GiftCertificate> readAll() {
    return giftDao.readAll();
  }

  @Override
  public int update(GiftCertificate gift) {
    return giftDao.update(gift);
  }

  @Override
  public Long delete(Long id) {
    return giftDao.delete(id);
  }
}
