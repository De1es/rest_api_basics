package com.epam.esm.dto.gift;

import com.epam.esm.tag.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

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
  private long id;
  private String name;
  private String description;
  private int price;
  private int duration;
  @JsonFormat(pattern = "yyyy-MM-dd") // OR @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  //OR TRY THIS
  //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  //@JsonSerialize(using = LocalDateTimeSerializer.class)
  //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime createDate;
  @JsonFormat(pattern = "yyyy-MM-dd") // OR @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  //OR TRY THIS
  //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  //@JsonSerialize(using = LocalDateTimeSerializer.class)
  //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime lastUpdateDate;
  private List<Tag> tags;
}
