package com.gitlab.rtc4j.restapi.services.impl;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TodoListDAO;
import com.gitlab.rtc4j.restapi.dtos.todo.list.AddTodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.list.UpdateTodoListRequest;
import com.gitlab.rtc4j.restapi.services.TodoListService;
import com.gitlab.rtc4j.restapi.transformers.TodoListTransformer;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class TodoListServiceImpl implements TodoListService {

  private final TodoListDAO todoListDAO;

  public TodoListServiceImpl(final TodoListDAO todoListDAO) {
    this.todoListDAO = todoListDAO;
  }

  @Override
  public @NotNull List<@NotNull @Valid TodoListResponse> findAll() {
    return todoListDAO
      .findAll()
      .stream()
      .map(TodoListTransformer::toResponse)
      .collect(toList());
  }

  @Override
  public @NotNull Optional<@Valid TodoListResponse> findById(@Min(1L) final long id) {
    return todoListDAO
      .findById(id)
      .map(TodoListTransformer::toResponse);
  }

  @Override
  public @NotNull @Valid TodoListResponse save(final @NotNull @Valid AddTodoListRequest request) {
    return TodoListTransformer
      .toResponse(todoListDAO.save(TodoListTransformer.from(request)));
  }

  @Override
  public @NotNull @Valid TodoListResponse update(final @NotNull @Valid UpdateTodoListRequest request) {
    return todoListDAO
      .findById(request.getId())
      .map(dbTodoList -> TodoListTransformer.from(dbTodoList, request))
      .map(todoListDAO::save)
      .map(TodoListTransformer::toResponse)
      .orElseThrow();
  }
}
