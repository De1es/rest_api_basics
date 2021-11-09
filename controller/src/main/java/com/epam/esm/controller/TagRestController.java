package com.epam.esm.controller;

import com.epam.esm.dto.tag.TagDTO;
import com.epam.esm.dto.tag.TagResponseDTO;
import com.epam.esm.mapper.TagDtoMapper;
import com.epam.esm.tag.Tag;
import com.epam.esm.tagservice.TagService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The TagRestController class
 */
@RestController
@RequestMapping(value = "/api/v1/tags", produces = MediaType.APPLICATION_JSON_VALUE)
@Data
public class TagRestController {

  private final TagService tagService;

  private final TagDtoMapper tagDtoMapper;

  /**
   * Create new Tag
   *
   * @param tagDTO TagCreation DTO
   * @return {@link TagResponseDTO}
   */
  @Operation(summary = "Create new Tag")
  @PostMapping("/")
  public ResponseEntity<TagResponseDTO> saveTag(@RequestBody TagDTO tagDTO) {
    final Tag tag = tagDtoMapper.mapDtoToCreateModel(tagDTO);
    final TagResponseDTO tagResponseDTO = tagDtoMapper.mapModelToResponseDto(tagService.save(tag));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(tagResponseDTO);
  }

  /**
   * Find tag by id
   *
   * @param id Tag id
   * @return {@link TagResponseDTO}
   */
  @Operation(summary = "Find tag by id")
  @GetMapping("/{id}")
  public ResponseEntity<TagResponseDTO> findTagById(@ApiParam(value = "Tag's id") @PathVariable Long id) {
    final TagResponseDTO tagResponseDTO = tagDtoMapper.mapModelToResponseDto(tagService.readById(id));
    return ResponseEntity.status(HttpStatus.OK)
        .body(tagResponseDTO);
  }

  /**
   * Delete Tag by id
   *
   * @param id Tag id
   * @return {@link Void}
   */
  @Operation(summary = "Delete Tag by id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTagById(@ApiParam(value = "Tag's id") @PathVariable Long id) {
    tagService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .build();
  }

}
