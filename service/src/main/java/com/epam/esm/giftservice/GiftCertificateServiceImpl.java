package com.epam.esm.giftservice;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.GiftCertificateDao;
import com.epam.esm.tag.Tag;
import com.epam.esm.tagdao.TagDao;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    List<Tag> tags = tagDao.readTagsForGift(id);
    gift.setTags(tags);
    return gift;
  }

  @Override
  public List<GiftCertificate> readAll() {
    return giftDao.readAll();
  }

  @Override
  @Transactional
  public GiftCertificate update(GiftCertificate gift) {
    giftDao.update(gift);
    List<String> newTagsNames = gift.getTags()
        .stream()
        .map(Tag::getName)
        .collect(Collectors.toList());
    System.out.println(newTagsNames);
    List<String> oldTagsNames = tagDao.readTagsForGift(gift.getId())
        .stream()
        .map(Tag::getName)
        .collect(Collectors.toList());
    System.out.println(oldTagsNames);
    oldTagsNames.stream()
        .filter(Predicate.not(newTagsNames::contains))
        .forEach((name) -> tagDao.deleteTagFromGiftCertificateByName(gift.getId(), name));

    newTagsNames.stream()
        .filter(Predicate.not(oldTagsNames::contains))
        .map((name) -> tagDao.create(new Tag(name)))
        .forEach((tag) -> tagDao.addTagToGiftCertificateByName(gift.getId(), tag.getName()));

    gift.setTags(tagDao.readTagsForGift(gift.getId()));
    return gift;
  }

  @Override
  public Long delete(Long id) {
    return giftDao.delete(id);
  }
}
