package com.epam.esm.controller;

import com.epam.esm.tag.Tag;
import com.epam.esm.tagservice.TagService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/tags", produces = MediaType.APPLICATION_JSON_VALUE)
@Data
public class TagRestController {

  private final TagService tagService;

  @PostMapping("/")
  public Tag saveTag(@RequestBody Tag tag) {
    return tagService.save(tag);
  }

  @GetMapping("/{id}")
  public Tag findTagById(@PathVariable Long id) {
    return tagService.readById(id);
  }

  @DeleteMapping("/{id}")
  public Long deleteTagById(@PathVariable Long id) {
    return tagService.delete(id);
  }

}
