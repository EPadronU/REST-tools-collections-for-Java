package com.gitlab.rtc4j.restapi.transformers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.Tag;
import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.domain.TodoList;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import lombok.experimental.UtilityClass;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class TodoItemTransformer {

  @NotNull
  @Valid
  public TodoItemResponse toResponse(@Valid TodoItem todoItem) {
    if (todoItem == null) {
      return null;
    }

    final @Valid TodoItem parent = todoItem.getParent();

    return TodoItemResponse
      .builder()
      .id(todoItem.getId())
      .name(todoItem.getName())
      .description(todoItem.getDescription())
      .weight(todoItem.getWeight())
      .status(todoItem.getStatus())
      .list(todoItem.getList().getId())
      .parent(parent != null ? parent.getId() : null)
      .children(todoItem.getChildren().stream().map(TodoItem::getId).collect(toSet()))
      .tags(todoItem.getTags().stream().map(Tag::getId).collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public TodoItem from(@NotNull @Valid final TodoItemRequest request) {
    return TodoItem
      .builder()
      .name(request.getName())
      .description(request.getDescription())
      .list(request
        .getListId()
        .map(id -> TodoList.builder().id(id).build())
        .orElse(null))
      .parent(request
        .getParentId()
        .map(id -> TodoItem.builder().id(id).build())
        .orElse(null))
      .weight(request.getWeight())
      .status(request.getStatus())
      .build();
  }

  @NotNull
  @Valid
  public TodoItem from(
    @NotNull @Valid final TodoItem todoItem,
    @NotNull @Valid final TodoItemRequest request) {
    final long listId = request.getListId().orElseThrow();

    return todoItem
      .toBuilder()
      .name(request.getName())
      .description(request.getDescription())
      .list(request
        .getListId()
        .map(id -> TodoList.builder().id(id).build())
        .orElse(null))
      .parent(request
        .getParentId()
        .map(id -> TodoItem.builder().id(id).build())
        .orElse(null))
      .children(todoItem
        .getChildren()
        .stream()
        .peek(child -> {
          child.setList(TodoList.builder().id(listId).build());
          child.setStatus(request.getStatus());
        })
        .collect(toSet()))
      .weight(request.getWeight())
      .status(request.getStatus())
      .build();
  }
}
