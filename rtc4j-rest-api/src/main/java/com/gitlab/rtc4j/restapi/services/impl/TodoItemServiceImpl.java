package com.gitlab.rtc4j.restapi.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TodoItemDAO;
import com.gitlab.rtc4j.restapi.dtos.todo.item.AddTodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.item.UpdateTodoItemRequest;
import com.gitlab.rtc4j.restapi.services.TodoItemService;
import com.gitlab.rtc4j.restapi.transformers.TodoItemTransformer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
public class TodoItemServiceImpl implements TodoItemService {

  private final TodoItemDAO todoItemDAO;

  public TodoItemServiceImpl(final TodoItemDAO todoItemDAO) {
    this.todoItemDAO = todoItemDAO;
  }

  @Override
  public @NotNull List<@NotNull @Valid TodoItemResponse> findAll() {
    return todoItemDAO
      .findAll()
      .stream()
      .map(TodoItemTransformer::toResponse)
      .collect(toList());
  }

  @Override
  public @NotNull Optional<@Valid TodoItemResponse> findById(@Min(1L) final long id) {
    return todoItemDAO
      .findById(id)
      .map(TodoItemTransformer::toResponse);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoItemResponse save(@NotNull @Valid final AddTodoItemRequest request) {
    return TodoItemTransformer
      .toResponse(todoItemDAO.save(TodoItemTransformer.from(request)));
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoItemResponse update(
    @Min(1L) final long id,
    @NotNull @Valid final UpdateTodoItemRequest request) {
    return todoItemDAO
      .findById(id)
      .map(dbTodoItem -> TodoItemTransformer.from(dbTodoItem, request))
      .map(todoItemDAO::save)
      .map(TodoItemTransformer::toResponse)
      .orElseThrow();
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid void deleteById(@Min(1L) final long id) {
    try {
      todoItemDAO.deleteById(id);
    } catch (EmptyResultDataAccessException ignore) {
      throw new NoSuchElementException();
    }
  }
}
