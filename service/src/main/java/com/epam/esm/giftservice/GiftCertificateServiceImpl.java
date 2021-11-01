package com.epam.esm.giftservice;

import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftdao.GiftCertificateDao;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class GiftCertificateServiceImpl implements GiftCertificateService {

  private final GiftCertificateDao giftCertificateDao;

  @Override
  public GiftCertificate save(GiftCertificate gift) {
    return getGiftCertificateDao().create(gift);
  }

  @Override
  public GiftCertificate readById(Long id) {
    return giftCertificateDao.readById(id);
  }

  @Override
  public List<GiftCertificate> readAll() {
    return giftCertificateDao.readAll();
  }

  @Override
  public int update(GiftCertificate gift) {
    return giftCertificateDao.update(gift);
  }

  @Override
  public Long delete(Long id) {
    return giftCertificateDao.delete(id);
  }
}
