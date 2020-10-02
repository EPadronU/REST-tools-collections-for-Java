package com.gitlab.rtc4j.restapi.mappers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.Tag;
import com.gitlab.rtc4j.restapi.dtos.tag.MetaTagTagsRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper extends BaseMapper {

  @NotNull
  @Valid
  @Mapping(target = "meta", expression = "java(!tag.getTags().isEmpty())")
  TagResponse toTagResponse(@NotNull @Valid final Tag tag);

  @NotNull
  @Valid
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "tags", ignore = true)
  Tag toTag(@NotNull @Valid final TagRequest request);

  @NotNull
  @Valid
  @Mapping(target = ".", source = "tag")
  @Mapping(target = "name", source = "request.name")
  Tag toTag(@NotNull @Valid final Tag tag, @NotNull @Valid final TagRequest request);

  @NotNull
  @Valid
  @Mapping(target = ".", source = "tag")
  @Mapping(target = "tags", source = "request.tags")
  Tag toTag(@NotNull @Valid final Tag tag, @NotNull @Valid final MetaTagTagsRequest request);
}
