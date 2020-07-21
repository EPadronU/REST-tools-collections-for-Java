package com.gitlab.rtc4j.restapi.transformers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TagDAO;
import com.gitlab.rtc4j.restapi.domain.Tag;
import com.gitlab.rtc4j.restapi.dtos.tag.AddTagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import com.gitlab.rtc4j.restapi.dtos.tag.UpdateTagRequest;
import lombok.experimental.UtilityClass;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class TagTransformer {

  @NotNull
  @Valid
  public TagResponse toResponse(@Valid Tag tag) {
    if (tag == null) {
      return null;
    }

    return TagResponse
      .builder()
      .id(tag.getId())
      .name(tag.getName())
      .isMeta(!tag.getTags().isEmpty())
      .tags(tag.getTags().stream().map(TagTransformer::toShallowResponse).collect(toSet()))
      .metaTags(tag.getTags().stream().map(TagTransformer::toShallowResponse).collect(toSet()))
      .taggedItems(tag
        .getTaggedItems()
        .stream()
        .map(TodoItemTransformer::toShallowResponse)
        .collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public TagResponse toShallowResponse(@NotNull @Valid Tag tag) {
    return TagResponse
      .builder()
      .id(tag.getId())
      .name(tag.getName())
      .isMeta(!tag.getTags().isEmpty())
      .tags(tag
        .getTags()
        .stream()
        .map(it -> TagResponse
          .builder()
          .id(it.getId())
          .name(it.getName())
          .build())
        .collect(toSet()))
      .metaTags(tag
        .getMetaTags()
        .stream()
        .map(it -> TagResponse
          .builder()
          .id(it.getId())
          .name(it.getName())
          .build())
        .collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public Tag from(@NotNull @Valid final AddTagRequest request, @NotNull TagDAO tagDAO) {
    return Tag
      .builder()
      .name(request.getName())
      .build();
  }

  @NotNull
  @Valid
  public Tag from(@NotNull @Valid final UpdateTagRequest request, @NotNull TagDAO tagDAO) {
    final Tag tag = tagDAO.findById(request.getId()).orElseThrow();

    tag.setName(request.getName());

    return tag;
  }
}
