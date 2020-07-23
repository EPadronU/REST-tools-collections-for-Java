package com.gitlab.rtc4j.restapi.services.impl;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TagDAO;
import com.gitlab.rtc4j.restapi.dtos.tag.AddTagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import com.gitlab.rtc4j.restapi.dtos.tag.UpdateTagRequest;
import com.gitlab.rtc4j.restapi.services.TagService;
import com.gitlab.rtc4j.restapi.transformers.TagTransformer;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class TagServiceImpl implements TagService {

  private final TagDAO tagDAO;

  public TagServiceImpl(final TagDAO tagDAO) {
    this.tagDAO = tagDAO;
  }

  @Override
  public @NotNull List<@NotNull @Valid TagResponse> findAll() {
    return tagDAO
      .findAll()
      .stream()
      .map(TagTransformer::toResponse)
      .collect(toList());
  }

  @Override
  public @NotNull Optional<@Valid TagResponse> findById(@Min(1L) final long id) {
    return tagDAO
      .findById(id)
      .map(TagTransformer::toResponse);
  }

  @Override
  public @NotNull @Valid TagResponse save(final @NotNull @Valid AddTagRequest request) {
    return TagTransformer
      .toResponse(tagDAO.save(TagTransformer.from(request)));
  }

  @Override
  public @NotNull @Valid TagResponse update(final @NotNull @Valid UpdateTagRequest request) {
    return tagDAO
      .findById(request.getId())
      .map(dbTag -> TagTransformer.from(dbTag, request))
      .map(tagDAO::save)
      .map(TagTransformer::toResponse)
      .orElseThrow();
  }
}
