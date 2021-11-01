package com.epam.esm.gift;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificate {
  private Long id;
  private String name;
  private String description;
  private int price;
  private int duration;
  @JsonFormat (pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createDate;
  @JsonFormat (pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastUpdateDate;

  public GiftCertificate(String name, String description, int price, int duration, LocalDateTime createDate,
                         LocalDateTime lastUpdateDate) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.duration = duration;
    this.createDate = createDate;
    this.lastUpdateDate = lastUpdateDate;
  }

}
