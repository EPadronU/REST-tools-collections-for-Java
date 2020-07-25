package com.gitlab.rtc4j.restapi.transformers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.Tag;
import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.dtos.tag.MetaTagTagsRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
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
      .tags(tag.getTags().stream().map(Tag::getId).collect(toSet()))
      .metaTags(tag.getMetaTags().stream().map(Tag::getId).collect(toSet()))
      .taggedItems(tag.getTaggedItems().stream().map(TodoItem::getId).collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public Tag from(@NotNull @Valid final TagRequest request) {
    return Tag
      .builder()
      .name(request.getName())
      .build();
  }

  @NotNull
  @Valid
  public Tag from(@NotNull @Valid final Tag tag, @NotNull @Valid final TagRequest request) {
    return tag
      .toBuilder()
      .name(request.getName())
      .build();
  }

  @NotNull
  @Valid
  public Tag from(
    @NotNull @Valid final Tag tag,
    @NotNull @Valid final MetaTagTagsRequest request) {

    return tag
      .toBuilder()
      .tags(request
        .getTags()
        .stream()
        .map(id -> Tag.builder().id(id).build())
        .collect(toSet()))
      .build();
  }
}
