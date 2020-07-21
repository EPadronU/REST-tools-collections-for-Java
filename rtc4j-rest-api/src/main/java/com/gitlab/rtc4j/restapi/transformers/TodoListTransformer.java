package com.gitlab.rtc4j.restapi.transformers;

import java.util.Collections;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TodoListDAO;
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
      .items(todoList.getItems().stream().map(TodoItemTransformer::toResponse).collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public TodoListResponse toShallowResponse(@NotNull @Valid TodoList todoList) {
    return TodoListResponse
      .builder()
      .id(todoList.getId())
      .name(todoList.getName())
      .description(todoList.getDescription())
      .items(Collections.emptySet())
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
    @NotNull @Valid final UpdateTodoListRequest request,
    @NotNull TodoListDAO todoListDAO) {
    final TodoList todoList = todoListDAO.findById(request.getId()).orElseThrow();

    todoList.setName(request.getName());
    todoList.setDescription(request.getDescription());

    return todoList;
  }
}
