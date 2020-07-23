package com.gitlab.rtc4j.restapi.transformers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.domain.TodoList;
import com.gitlab.rtc4j.restapi.dtos.todo.list.AddTodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.list.UpdateTodoListRequest;
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
  public TodoList from(@NotNull @Valid final AddTodoListRequest request) {
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
    @NotNull @Valid final UpdateTodoListRequest request) {
    if (todoList.getId() != request.getId()) {
      throw new IllegalArgumentException("The IDs don't match");
    }

    return todoList
      .toBuilder()
      .name(request.getName())
      .description(request.getDescription())
      .build();
  }
}
