package com.epam.esm.dto.tag;

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
  private Long id;
  private String name;
}
