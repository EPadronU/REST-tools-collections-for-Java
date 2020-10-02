package com.gitlab.rtc4j.restapi.mappers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.domain.TodoList;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemRequest;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.stream.Collectors.toSet;

public abstract class TodoItemMapperDecorator implements TodoItemMapper {

  @Autowired
  private TodoItemMapper delegate;

  @Override
  @NotNull
  @Valid
  public TodoItem toTodoItem(
    @NotNull @Valid final TodoItem todoItem,
    @NotNull @Valid final TodoItemRequest request) {
    final TodoItem target = delegate.toTodoItem(todoItem, request);

    target.setChildren(todoItem
      .getChildren()
      .stream()
      .peek(child -> {
        child.setList(TodoList.builder().id(request.getListId().orElseThrow()).build());
        child.setStatus(request.getStatus());
      })
      .collect(toSet()));

    return target;
  }
}
