package com.gitlab.rtc4j.restapi.resources;

import java.util.NoSuchElementException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.tag.MetaTagTagsRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import com.gitlab.rtc4j.restapi.services.TagService;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/tag")
public class TagController extends CrudController<TagRequest, TagResponse> {

  private final TagService service;

  public TagController(final TagService service) {
    super(service);

    this.service = service;
  }

  @PutMapping("/{id}/tags")
  public ResponseEntity<TagResponse> updateTags(
    @PathVariable @Min(1L) long id,
    @NotNull @Valid @RequestBody MetaTagTagsRequest request) {
    try {
      return ResponseEntity.ok(service.update(id, request));
    } catch (NoSuchElementException e) {
      log.error(e);

      return ResponseEntity.notFound().build();
    } catch (DataAccessException e) {
      log.error(e);

      return ResponseEntity.badRequest().build();
    }
  }
}
