package com.gitlab.rtc4j.restapi.transformers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.Tag;
import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.domain.TodoList;
import com.gitlab.rtc4j.restapi.dtos.todo.item.AddTodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.item.UpdateTodoItemRequest;
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

    return TodoItemResponse
      .builder()
      .id(todoItem.getId())
      .name(todoItem.getName())
      .description(todoItem.getDescription())
      .weight(todoItem.getWeight())
      .status(todoItem.getStatus())
      .list(todoItem.getList().getId())
      .parent(todoItem.getParent().getId())
      .children(todoItem.getChildren().stream().map(TodoItem::getId).collect(toSet()))
      .tags(todoItem.getTags().stream().map(Tag::getId).collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public TodoItem from(@NotNull @Valid final AddTodoItemRequest request) {
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
    @NotNull @Valid final UpdateTodoItemRequest request) {
    if (todoItem.getId() != request.getId()) {
      throw new IllegalArgumentException("The IDs don't match");
    }

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
      .weight(request.getWeight())
      .status(request.getStatus())
      .build();
  }
}