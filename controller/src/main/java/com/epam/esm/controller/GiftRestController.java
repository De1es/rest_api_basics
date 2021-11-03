package com.epam.esm.controller;

import com.epam.esm.dto.gift.GiftCertificateDTO;
import com.epam.esm.dto.gift.GiftCertificateResponseDTO;
import com.epam.esm.giftservice.GiftCertificateService;
import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.mapper.GiftCertificateDtoMapperImpl;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The GiftRestController class
 */
@RestController
@RequestMapping(value = "/api/gifts", produces = MediaType.APPLICATION_JSON_VALUE)
@Data
public class GiftRestController {

  private final GiftCertificateService giftCertificateService;

  private final GiftCertificateDtoMapperImpl giftDtoMapper;


  /**
   * Get list of GiftCertificates
   *
   * @param tagName    Tag name
   * @param partOfName part of GiftCertificate name
   * @param limit      count of read items
   * @param sortBy     sort by name/last_update_date/create_date
   * @param sortOrder  sort order ASC/DESC
   * @return list of {@link GiftCertificate}
   */
  @GetMapping("/")
  public ResponseEntity<List<GiftCertificate>> getGiftsList(@RequestParam(name = "tagName", required = false) String tagName,
                                                            @RequestParam(name = "partOfName", required = false) String partOfName,
                                                            @RequestParam(name = "limit", defaultValue = "10") int limit,
                                                            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
                                                            @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(giftCertificateService.list(tagName, partOfName, limit, sortBy,
            sortOrder));
  }

  /**
   * Create new Gift Certificate
   *
   * @param giftCertificateDTO GiftCertificateCreation DTO
   * @return {@link GiftCertificateResponseDTO}
   */
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GiftCertificateResponseDTO> saveGift(@RequestBody GiftCertificateDTO giftCertificateDTO) {
    final GiftCertificate giftCertificate = giftDtoMapper.mapDtoToCreateModel(giftCertificateDTO);
    final GiftCertificateResponseDTO giftCertificateResponseDTO =
        giftDtoMapper.mapModelToResponseDto(giftCertificateService.save(giftCertificate));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(giftCertificateResponseDTO);
  }


  /**
   * Find gift by id
   *
   * @param id GiftCertificate id
   * @return {@link GiftCertificateResponseDTO}
   */
  @GetMapping("/{id}")
  public ResponseEntity<GiftCertificateResponseDTO> findGiftById(@PathVariable Long id) {
    final GiftCertificateResponseDTO giftCertificateResponseDTO =
        giftDtoMapper.mapModelToResponseDto(giftCertificateService.readById(id));
    return ResponseEntity.status(HttpStatus.OK)
        .body(giftCertificateResponseDTO);
  }

  /**
   * Update Gift Certificate
   *
   * @param giftCertificateDTO GiftCertificateUpdate DTO
   * @return {@link GiftCertificateResponseDTO}
   */
  @PostMapping("/{id}")
  public ResponseEntity<GiftCertificateResponseDTO> updateGift(@PathVariable("id") Long id,
                                                               @RequestBody GiftCertificateDTO giftCertificateDTO) {
    final GiftCertificate giftCertificate = giftDtoMapper.mapDtoToUpdateModel(id, giftCertificateDTO);
    giftCertificate.setId(id);
    final GiftCertificateResponseDTO giftCertificateResponseDTO =
        giftDtoMapper.mapModelToResponseDto(giftCertificateService.update(giftCertificate));
    return ResponseEntity.status(HttpStatus.OK)
        .body(giftCertificateResponseDTO);
  }

  /**
   * Delete GiftCertificate by id
   *
   * @param id GiftCertificate id
   * @return {@link Long}
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGiftById(@PathVariable Long id) {
    giftCertificateService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
