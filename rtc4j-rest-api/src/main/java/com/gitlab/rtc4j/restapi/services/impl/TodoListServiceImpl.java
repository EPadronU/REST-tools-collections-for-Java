package com.gitlab.rtc4j.restapi.services.impl;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TodoListDAO;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import com.gitlab.rtc4j.restapi.services.TodoListService;
import com.gitlab.rtc4j.restapi.transformers.TodoListTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoListResponse save(@NotNull @Valid final TodoListRequest request) {
    return TodoListTransformer
      .toResponse(todoListDAO.save(TodoListTransformer.from(request)));
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoListResponse update(
    @Min(1L) final long id,
    @NotNull @Valid final TodoListRequest request) {
    return todoListDAO
      .findById(id)
      .map(dbTodoList -> TodoListTransformer.from(dbTodoList, request))
      .map(todoListDAO::save)
      .map(TodoListTransformer::toResponse)
      .orElseThrow();
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid void deleteById(@Min(1L) final long id) {
    todoListDAO.deleteById(id);
  }
}
