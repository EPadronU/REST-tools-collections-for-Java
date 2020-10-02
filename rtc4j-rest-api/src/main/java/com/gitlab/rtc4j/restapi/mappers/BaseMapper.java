package com.gitlab.rtc4j.restapi.mappers;

import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.Tag;
import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.domain.TodoList;
import org.mapstruct.Mapper;

import static java.util.stream.Collectors.toSet;

@Mapper
public interface BaseMapper {

  @NotNull
  default Set<@NotNull Long> extractTagsId(@NotNull final Set<@NotNull @Valid Tag> tags) {
    return tags
      .stream()
      .map(Tag::getId)
      .collect(toSet());
  }

  @NotNull
  default Set<@NotNull Long> extractTodoItemsId(@NotNull final Set<@NotNull @Valid TodoItem> tags) {
    return tags
      .stream()
      .map(TodoItem::getId)
      .collect(toSet());
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  @Valid
  default TodoItem wrapTodoItemId(@NotNull final Optional<Long> todoItemId) {
    return todoItemId
      .map(id -> TodoItem.builder().id(id).build())
      .orElse(null);
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  @Valid
  default TodoList wrapTodoListId(@NotNull final Optional<Long> todoListId) {
    return todoListId
      .map(id -> TodoList.builder().id(id).build())
      .orElse(null);
  }

  @NotNull
  default Set<@NotNull @Valid Tag> wrapTagsId(@NotNull final Set<@NotNull Long> ids) {
    return ids
      .stream()
      .map(id -> Tag.builder().id(id).build())
      .collect(toSet());
  }
}
