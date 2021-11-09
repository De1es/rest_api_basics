package com.epam.esm.dto.gift;

import com.epam.esm.tag.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * The GiftCertificateDTO class
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificateDTO {
  @ApiModelProperty(notes = "Name of the GiftCertificate", name = "name", required = true)
  private String name;
  @ApiModelProperty(notes = "Description of the GiftCertificate", name = "description", required = true)
  private String description;
  @ApiModelProperty(notes = "Price of the GiftCertificate", name = "price", required = true)
  private int price;
  @ApiModelProperty(notes = "Duration of the GiftCertificate", name = "duration", required = true)
  private int duration;
  @ApiModelProperty(notes = "List of GiftCertificate tags", name = "tags", required = true)
  private List<Tag> tags = new LinkedList<>();
}
