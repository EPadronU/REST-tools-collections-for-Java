package com.gitlab.rtc4j.restapi.transformers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.domain.TodoList;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import lombok.experimental.UtilityClass;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class TodoListTransformer {

  @NotNull
  @Valid
  public TodoListResponse toResponse(@NotNull @Valid TodoList todoList) {
    return TodoListResponse
      .builder()
      .id(todoList.getId())
      .name(todoList.getName())
      .description(todoList.getDescription())
      .items(todoList.getItems().stream().map(TodoItem::getId).collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public TodoList from(@NotNull @Valid final TodoListRequest request) {
    return TodoList
      .builder()
      .name(request.getName())
      .description(request.getDescription())
      .build();
  }

  @NotNull
  @Valid
  public TodoList from(
    @NotNull @Valid final TodoList todoList,
    @NotNull @Valid final TodoListRequest request) {
    return todoList
      .toBuilder()
      .name(request.getName())
      .description(request.getDescription())
      .build();
  }
}
