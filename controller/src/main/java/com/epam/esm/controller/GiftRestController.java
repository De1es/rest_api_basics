package com.epam.esm.controller;

import com.epam.esm.dto.gift.GiftCertificateDTO;
import com.epam.esm.dto.gift.GiftCertificateResponseDTO;
import com.epam.esm.giftservice.GiftCertificateService;
import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.mapper.GiftCertificateDtoMapperImpl;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(value = "/api/v1/gifts", produces = MediaType.APPLICATION_JSON_VALUE)
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
  @Operation(summary = "Get list of GiftCertificates")
  @GetMapping("/")
  public ResponseEntity<List<GiftCertificateResponseDTO>> getGiftsList(
      @ApiParam(value = "Search tag name")
      @RequestParam(name = "tagName", required = false)
          String tagName,
      @ApiParam(value = "Part of GiftCertificate name")
      @RequestParam(name = "partOfName", required = false)
          String partOfName,
      @ApiParam(value = "Search limit", defaultValue = "10")
      @RequestParam(name = "limit", defaultValue = "10")
          int limit,
      @ApiParam(value = "Field to sort by", defaultValue = "name", allowableValues = "name, create_date, " +
          "last_update_date")
      @RequestParam(name = "sortBy", defaultValue = "name")
          String sortBy,
      @ApiParam(value = "Sorting order", defaultValue = "ASC", allowableValues = "ASC, DESC")
      @RequestParam(name = "sortOrder", defaultValue = "ASC")
          String sortOrder) {
    List<GiftCertificateResponseDTO> responseDTOS =
        giftDtoMapper.mapModelListToDtoList(giftCertificateService.list(tagName, partOfName, limit, sortBy,
            sortOrder));
    return ResponseEntity.status(HttpStatus.OK)
        .body(responseDTOS);
  }

  /**
   * Create new Gift Certificate
   *
   * @param giftCertificateDTO GiftCertificateCreation DTO
   * @return {@link GiftCertificateResponseDTO}
   */
  @Operation(summary = "Create new Gift Certificate")
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
  @Operation(summary = "Find gift by id")
  @GetMapping("/{id}")
  public ResponseEntity<GiftCertificateResponseDTO> findGiftById(@ApiParam(value = "GiftCertificate id")
                                                                 @PathVariable Long id) {
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
  @Operation(summary = "Update Gift Certificate")
  @PostMapping("/{id}")
  public ResponseEntity<GiftCertificateResponseDTO> updateGift(@ApiParam(value = "GiftCertificate id") @PathVariable(
      "id") Long id, @RequestBody GiftCertificateDTO giftCertificateDTO) {
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
  @Operation(summary = "Delete GiftCertificate by id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGiftById(@ApiParam(value = "GiftCertificate id") @PathVariable Long id) {
    giftCertificateService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
