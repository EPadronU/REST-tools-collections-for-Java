package com.gitlab.rtc4j.restapi.resources;

import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.tag.AddTagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import com.gitlab.rtc4j.restapi.dtos.tag.UpdateTagRequest;
import com.gitlab.rtc4j.restapi.services.TagService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Log4j2
@RestController
@RequestMapping("/api/tag")
public class TagController {

  private final TagService service;

  public TagController(final TagService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<@Valid TagResponse> create(
    @NotNull @Valid @RequestBody AddTagRequest request) {
    return ResponseEntity.status(CREATED).body(service.save(request));
  }

  @GetMapping
  public ResponseEntity<@NotNull List<@Valid TagResponse>> retrieveAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<@Valid TagResponse> retrieveById(@Min(1) @PathVariable final long id) {
    return ResponseEntity.of(service.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<@Valid TagResponse> updateById(
    @Min(1) @PathVariable final long id,
    @NotNull @Valid @RequestBody UpdateTagRequest request) {
    try {
      return ResponseEntity.ok(service.update(id, request));
    } catch (NoSuchElementException e) {
      log.error(e);

      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable final @Min(1) long id) {
    try {
      service.deleteById(id);
    } catch (NoSuchElementException e) {
      log.error(e);

      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
}
