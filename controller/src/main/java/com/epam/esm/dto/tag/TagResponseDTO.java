package com.epam.esm.dto.tag;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The TagDTO class
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseDTO {
  @ApiModelProperty(notes = "Id of the Tag", name = "id")
  private Long id;
  @ApiModelProperty(notes = "Name of the Tag", name = "name")
  private String name;
}
