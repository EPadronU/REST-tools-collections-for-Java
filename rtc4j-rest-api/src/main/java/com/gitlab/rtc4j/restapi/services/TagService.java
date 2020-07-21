package com.gitlab.rtc4j.restapi.services;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.tag.AddTagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import com.gitlab.rtc4j.restapi.dtos.tag.UpdateTagRequest;

public interface TagService {

  @NotNull
  List<@NotNull @Valid TagResponse> findAll();

  @NotNull
  Optional<@Valid TagResponse> findById(@Min(1L) final long id);

  @NotNull
  @Valid
  TagResponse save(@NotNull @Valid final AddTagRequest request);

  @NotNull
  @Valid
  TagResponse update(@NotNull @Valid final UpdateTagRequest request);
}
