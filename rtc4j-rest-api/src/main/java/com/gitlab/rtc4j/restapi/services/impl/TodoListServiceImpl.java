package com.gitlab.rtc4j.restapi.services.impl;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TodoListDAO;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import com.gitlab.rtc4j.restapi.mappers.TodoListMapper;
import com.gitlab.rtc4j.restapi.services.TodoListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
public class TodoListServiceImpl implements TodoListService {

  private final TodoListDAO todoListDAO;

  private final TodoListMapper todoListMapper;

  public TodoListServiceImpl(
    final TodoListDAO todoListDAO,
    final TodoListMapper todoListMapper) {
    this.todoListDAO = todoListDAO;
    this.todoListMapper = todoListMapper;
  }

  @Override
  public @NotNull List<@NotNull @Valid TodoListResponse> findAll() {
    return todoListDAO
      .findAll()
      .stream()
      .map(todoListMapper::toTodoListResponse)
      .collect(toList());
  }

  @Override
  public @NotNull Optional<@Valid TodoListResponse> findById(@Min(1L) final long id) {
    return todoListDAO
      .findById(id)
      .map(todoListMapper::toTodoListResponse);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoListResponse save(@NotNull @Valid final TodoListRequest request) {
    return todoListMapper
      .toTodoListResponse(todoListDAO.save(todoListMapper.toTodoList(request)));
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoListResponse update(
    @Min(1L) final long id,
    @NotNull @Valid final TodoListRequest request) {
    return todoListDAO
      .findById(id)
      .map(dbTodoList -> todoListMapper.toTodoList(dbTodoList, request))
      .map(todoListDAO::save)
      .map(todoListMapper::toTodoListResponse)
      .orElseThrow();
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid void deleteById(@Min(1L) final long id) {
    todoListDAO.deleteById(id);
  }
}
