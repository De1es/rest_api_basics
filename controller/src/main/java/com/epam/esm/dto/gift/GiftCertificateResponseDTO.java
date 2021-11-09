package com.epam.esm.dto.gift;

import com.epam.esm.tag.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The GiftCertificateResponseDTO class
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificateResponseDTO {
  @ApiModelProperty(notes = "Id of the GiftCertificate", name = "id")
  private long id;
  @ApiModelProperty(notes = "Name of the GiftCertificate", name = "name")
  private String name;
  @ApiModelProperty(notes = "Description of the GiftCertificate", name = "description")
  private String description;
  @ApiModelProperty(notes = "Price of the GiftCertificate", name = "price")
  private int price;
  @ApiModelProperty(notes = "Duration of the GiftCertificate", name = "duration")
  private int duration;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(notes = "GiftCertificate creation date", name = "createDate")
  private LocalDateTime createDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(notes = "GiftCertificate last updating date", name = "lastUpdateDate")
  private LocalDateTime lastUpdateDate;
  @ApiModelProperty(notes = "List of GiftCertificate tags", name = "tags", required = true)
  private List<Tag> tags;
}
