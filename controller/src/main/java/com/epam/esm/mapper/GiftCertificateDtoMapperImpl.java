package com.epam.esm.mapper;

import com.epam.esm.dto.gift.GiftCertificateDTO;
import com.epam.esm.dto.gift.GiftCertificateResponseDTO;
import com.epam.esm.gift.GiftCertificate;
import com.epam.esm.giftservice.GiftCertificateService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The GiftCertificateDtoMapper class
 */
@Component
@Data
public class GiftCertificateDtoMapperImpl implements DtoMapper<GiftCertificateDTO,
    GiftCertificateResponseDTO, GiftCertificate> {

  private final GiftCertificateService giftCertificateService;

  @Override
  public GiftCertificate mapDtoToCreateModel(GiftCertificateDTO dto) {
    final GiftCertificate model = new GiftCertificate();
    model.setName(dto.getName());
    model.setDescription(dto.getDescription());
    model.setDuration(dto.getDuration());
    model.setPrice(dto.getPrice());
    model.setCreateDate(LocalDateTime.now());
    model.setLastUpdateDate(LocalDateTime.now());
    model.setTags(dto.getTags());

    return model;
  }

  @Override
  public GiftCertificate mapDtoToUpdateModel(long id, GiftCertificateDTO dto) {
    final GiftCertificate model = giftCertificateService.readById(id);
    model.setName(dto.getName());
    model.setDescription(dto.getDescription());
    model.setDuration(dto.getDuration());
    model.setPrice(dto.getPrice());
    model.setLastUpdateDate(LocalDateTime.now());
    model.setTags(dto.getTags());

    return model;
  }

  @Override
  public GiftCertificateResponseDTO mapModelToResponseDto(GiftCertificate model) {
    return GiftCertificateResponseDTO.builder()
        .id(model.getId())
        .name(model.getName())
        .description(model.getDescription())
        .price(model.getPrice())
        .duration(model.getDuration())
        .createDate(model.getCreateDate())
        .lastUpdateDate(model.getLastUpdateDate())
        .tags(model.getTags())
        .build();
  }

  @Override
  public List<GiftCertificateResponseDTO> mapModelListToDtoList(List<GiftCertificate> models) {
    return models.stream().map(this::mapModelToResponseDto).collect(Collectors.toList());
  }
}
