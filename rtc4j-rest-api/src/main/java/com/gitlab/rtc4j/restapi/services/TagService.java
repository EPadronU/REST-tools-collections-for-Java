package com.gitlab.rtc4j.restapi.services;

import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.tag.MetaTagTagsRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;

public interface TagService extends CrudService<TagRequest, TagResponse> {

  @NotNull
  Optional<@Valid TagResponse> update(
    @Min(1L) final long id,
    @NotNull @Valid final MetaTagTagsRequest request);
}
