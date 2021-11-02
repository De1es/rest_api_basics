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
public class TagDTO {

  @ApiModelProperty(notes = "Name of the Tag", name = "name", required = true)
  private String name;
}
