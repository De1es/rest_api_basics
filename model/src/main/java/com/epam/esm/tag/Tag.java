package com.epam.esm.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
  private Long id;
  private String name;

  public Tag(String name) {
    this.name = name;
  }
}
