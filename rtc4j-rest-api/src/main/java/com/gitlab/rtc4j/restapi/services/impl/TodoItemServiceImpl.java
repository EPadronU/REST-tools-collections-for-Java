package com.gitlab.rtc4j.restapi.services.impl;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TodoItemDAO;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemTagsRequest;
import com.gitlab.rtc4j.restapi.mappers.TodoItemMapper;
import com.gitlab.rtc4j.restapi.services.TodoItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
public class TodoItemServiceImpl implements TodoItemService {

  private final TodoItemDAO todoItemDAO;

  private final TodoItemMapper todoItemMapper;

  public TodoItemServiceImpl(
    final TodoItemDAO todoItemDAO,
    final TodoItemMapper todoItemMapper) {
    this.todoItemDAO = todoItemDAO;
    this.todoItemMapper = todoItemMapper;
  }

  @Override
  public @NotNull List<@NotNull @Valid TodoItemResponse> findAll() {
    return todoItemDAO
      .findAll()
      .stream()
      .map(todoItemMapper::toTodoItemResponse)
      .collect(toList());
  }

  @Override
  public @NotNull Optional<@Valid TodoItemResponse> findById(@Min(1L) final long id) {
    return todoItemDAO
      .findById(id)
      .map(todoItemMapper::toTodoItemResponse);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoItemResponse save(@NotNull @Valid final TodoItemRequest request) {
    return todoItemMapper
      .toTodoItemResponse(todoItemDAO.save(todoItemMapper.toTodoItem(request)));
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoItemResponse update(
    @Min(1L) final long id,
    @NotNull @Valid final TodoItemRequest request) {
    return todoItemDAO
      .findById(id)
      .map(dbTodoItem -> todoItemMapper.toTodoItem(dbTodoItem, request))
      .map(todoItemDAO::save)
      .map(todoItemMapper::toTodoItemResponse)
      .orElseThrow();
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid void deleteById(@Min(1L) final long id) {
    todoItemDAO.deleteById(id);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TodoItemResponse update(
    @Min(1L) final long id,
    @NotNull @Valid final TodoItemTagsRequest request) {
    return todoItemDAO
      .findById(id)
      .map(dbTodoItem -> todoItemMapper.toTodoItem(dbTodoItem, request))
      .map(todoItemDAO::save)
      .map(todoItemMapper::toTodoItemResponse)
      .orElseThrow();
  }
}
