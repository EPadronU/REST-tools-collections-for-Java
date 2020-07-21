package com.gitlab.rtc4j.restapi.transformers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TodoItemDAO;
import com.gitlab.rtc4j.restapi.daos.TodoListDAO;
import com.gitlab.rtc4j.restapi.domain.TodoItem;
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
      .list(TodoListTransformer.toShallowResponse(todoItem.getList()))
      .parent(TodoItemTransformer.toShallowResponse(todoItem.getParent()))
      .children(todoItem
        .getChildren()
        .stream()
        .map(TodoItemTransformer::toShallowResponse)
        .collect(toSet()))
      .tags(todoItem.getTags().stream().map(TagTransformer::toShallowResponse).collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public TodoItemResponse toShallowResponse(@Valid TodoItem todoItem) {
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
      .list(TodoListTransformer.toShallowResponse(todoItem.getList()))
      .children(todoItem
        .getChildren()
        .stream()
        .map(it -> TodoItemResponse
          .builder()
          .id(it.getId())
          .name(it.getName())
          .description(it.getDescription())
          .weight(it.getWeight())
          .status(it.getStatus())
          .tags(it.getTags().stream().map(TagTransformer::toShallowResponse).collect(toSet()))
          .build())
        .collect(toSet()))
      .tags(todoItem.getTags().stream().map(TagTransformer::toShallowResponse).collect(toSet()))
      .build();
  }

  @NotNull
  @Valid
  public TodoItem from(
    @NotNull @Valid final AddTodoItemRequest request,
    @NotNull TodoListDAO todoListDAO,
    @NotNull TodoItemDAO todoItemDAO) {
    return TodoItem
      .builder()
      .name(request.getName())
      .description(request.getDescription())
      .list(todoListDAO.findById(request.getListId()).orElseThrow())
      .parent(todoItemDAO.findById(request.getParentId()).orElseThrow())
      .weight(request.getWeight())
      .status(request.getStatus())
      .build();
  }

  @NotNull
  @Valid
  public TodoItem from(
    @NotNull @Valid final UpdateTodoItemRequest request,
    @NotNull TodoListDAO todoListDAO,
    @NotNull TodoItemDAO todoItemDAO) {
    final TodoItem todoItem = todoItemDAO.findById(request.getId()).orElseThrow();

    todoItem.setName(request.getName());
    todoItem.setDescription(request.getDescription());
    todoItem.setList(todoListDAO.findById(request.getListId()).orElseThrow());
    todoItem.setParent(todoItemDAO.findById(request.getParentId()).orElseThrow());
    todoItem.setWeight(request.getWeight());
    todoItem.setStatus(request.getStatus());

    return todoItem;
  }
}
