package com.gitlab.rtc4j.restapi.resources;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.services.CrudService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.CREATED;

@Log4j2
public class CrudController<Request, Response> {

  private final CrudService<Request, Response> service;

  protected CrudController(final CrudService<Request, Response> service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<@Valid Response> create(
    @NotNull @Valid @RequestBody final Request request) {
    return ResponseEntity.status(CREATED).body(service.save(request));
  }

  @GetMapping
  public ResponseEntity<@NotNull List<@Valid Response>> retrieveAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<@Valid Response> retrieveById(@Min(1L) @PathVariable final long id) {
    return ResponseEntity.of(service.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<@Valid Response> updateById(
    @Min(1) @PathVariable final long id,
    @NotNull @Valid @RequestBody final Request request) {
    return ResponseEntity.ok(service.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable final @Min(1L) long id) {
    service.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
