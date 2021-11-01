package com.epam.esm.controller;

import com.epam.esm.giftservice.GiftCertificateService;
import com.epam.esm.gift.GiftCertificate;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/gifts", produces = MediaType.APPLICATION_JSON_VALUE)
@Data
public class GiftRestController {

  private final GiftCertificateService giftCertificateService;

  @GetMapping("/")
  public List<GiftCertificate> findALlGifts() {
    return giftCertificateService.readAll();
  }

  @PostMapping("/")
  public GiftCertificate saveGift(@RequestBody GiftCertificate gift) {
    return giftCertificateService.save(gift);
  }

  @GetMapping("/{id}")
  public GiftCertificate findGiftById(@PathVariable Long id) {
    return giftCertificateService.readById(id);
  }

  @PostMapping("/{id}")
  public int updateGift(@RequestBody GiftCertificate gift) {
    return giftCertificateService.update(gift);
  }

  @DeleteMapping("/{id}")
  public Long deleteGiftById(@PathVariable Long id) {
    return giftCertificateService.delete(id);
  }

}
