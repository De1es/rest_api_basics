package com.epam.esm.mapper;

import com.epam.esm.dto.tag.TagDTO;
import com.epam.esm.dto.tag.TagResponseDTO;
import com.epam.esm.tag.Tag;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The TagDtoMapper class
 */
@Component
@Data
public class TagDtoMapper implements DtoMapper<TagDTO, TagResponseDTO, Tag> {

  @Override
  public Tag mapDtoToCreateModel(TagDTO dto) {
    final Tag model = new Tag();
    model.setName(dto.getName());

    return model;
  }

  @Override
  public Tag mapDtoToUpdateModel(long id, TagDTO dto) {
    final Tag model = new Tag();
    model.setName(dto.getName());

    return model;
  }

  @Override
  public TagResponseDTO mapModelToResponseDto(Tag model) {
    if (model == null) {
      return null;
    }
    return TagResponseDTO.builder()
        .id(model.getId())
        .name(model.getName())
        .build();
  }

  @Override
  public List<TagResponseDTO> mapModelListToDtoList(List<Tag> models) {
    return models.stream().map(this::mapModelToResponseDto).collect(Collectors.toList());
  }
}
