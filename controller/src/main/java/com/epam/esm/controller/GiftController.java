package com.epam.esm.controller;

import com.epam.esm.GiftCertificateService;
import com.epam.esm.gift.GiftCertificate;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gifts", produces = MediaType.APPLICATION_JSON_VALUE)
@Data
public class GiftController {

  private final GiftCertificateService giftCertificateService;

  @GetMapping("/")
  public List<GiftCertificate> findALlGifts() {
    return giftCertificateService.readAll();
  }

  @PostMapping("/")
  public GiftCertificate saveGift(@RequestBody GiftCertificate gift) {
    return giftCertificateService.save(gift);
  }

  @PatchMapping("/}")
  public int updateGift(@RequestBody GiftCertificate gift) {
    return giftCertificateService.update(gift);
  }

  @GetMapping("/{id}")
  public GiftCertificate findGiftById(@PathVariable Long id) {
    return giftCertificateService.readById(id);
  }

  @DeleteMapping("/{id}")
  public Long deleteById (@PathVariable Long id) {
    return giftCertificateService.delete(id);
  }

}
