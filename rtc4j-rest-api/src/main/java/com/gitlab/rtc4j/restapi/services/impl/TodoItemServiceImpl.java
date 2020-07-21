package com.gitlab.rtc4j.restapi.services.impl;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TodoItemDAO;
import com.gitlab.rtc4j.restapi.daos.TodoListDAO;
import com.gitlab.rtc4j.restapi.dtos.todo.item.AddTodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.item.UpdateTodoItemRequest;
import com.gitlab.rtc4j.restapi.services.TodoItemService;
import com.gitlab.rtc4j.restapi.transformers.TodoItemTransformer;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class TodoItemServiceImpl implements TodoItemService {

  private final TodoListDAO todoListDAO;

  private final TodoItemDAO todoItemDAO;

  public TodoItemServiceImpl(
    final TodoListDAO todoListDAO,
    final TodoItemDAO todoItemDAO) {
    this.todoListDAO = todoListDAO;
    this.todoItemDAO = todoItemDAO;
  }

  @Override
  public @NotNull List<@NotNull @Valid TodoItemResponse> findAll() {
    return todoItemDAO.findAll().stream().map(TodoItemTransformer::toResponse).collect(toList());
  }

  @Override
  public @NotNull Optional<@Valid TodoItemResponse> findById(@Min(1L) final long id) {
    return todoItemDAO.findById(id).map(TodoItemTransformer::toResponse);
  }

  @Override
  public @NotNull @Valid TodoItemResponse save(final @NotNull @Valid AddTodoItemRequest request) {
    return TodoItemTransformer.toResponse
      (todoItemDAO.save(TodoItemTransformer.from(request, todoListDAO, todoItemDAO)));
  }

  @Override
  public @NotNull @Valid TodoItemResponse update(final @NotNull @Valid UpdateTodoItemRequest request) {
    return TodoItemTransformer.toResponse(
      todoItemDAO.save(TodoItemTransformer.from(request, todoListDAO, todoItemDAO)));
  }
}
