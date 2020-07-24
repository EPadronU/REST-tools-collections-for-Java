package com.gitlab.rtc4j.restapi.resources;

import com.gitlab.rtc4j.restapi.dtos.tag.TagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import com.gitlab.rtc4j.restapi.services.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
public class TagController extends CrudController<TagRequest, TagResponse> {

  public TagController(final TagService service) {
    super(service);
  }
}
