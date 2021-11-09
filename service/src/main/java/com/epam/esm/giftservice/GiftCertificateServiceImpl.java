package com.epam.esm.giftservice;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.GiftCertificateDao;
import com.epam.esm.tagdao.TagDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {

  private final GiftCertificateDao giftDao;
  private final TagDao tagDao;

  @Override
  @Transactional
  public GiftCertificate save(GiftCertificate gift) {
    GiftCertificate newGift = giftDao.create(gift);
    return tagDao.updateTagsForGift(newGift);
  }

  @Override
  @Transactional
  public GiftCertificate readById(Long id) {
    GiftCertificate gift = giftDao.readById(id);
    gift.setTags(tagDao.readTagsForGift(id));
    return gift;
  }


  @Override
  public List<GiftCertificate> list(String tagName, String partOfName, int limit, String sortBy, String sortOrder) {
    return giftDao.list(tagName, partOfName, limit, sortBy, sortOrder);
  }

  @Override
  @Transactional
  public GiftCertificate update(GiftCertificate gift) {
    giftDao.update(gift);
    return tagDao.updateTagsForGift(gift);
  }

  @Override
  public Long delete(Long id) {
    return giftDao.delete(id);
  }
}
