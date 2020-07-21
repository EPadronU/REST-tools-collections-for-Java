package com.gitlab.rtc4j.restapi.services;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.todo.item.AddTodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.item.UpdateTodoItemRequest;

public interface TodoItemService {

  @NotNull
  List<@NotNull @Valid TodoItemResponse> findAll();

  @NotNull
  Optional<@Valid TodoItemResponse> findById(@Min(1L) final long id);

  @NotNull
  @Valid
  TodoItemResponse save(@NotNull @Valid final AddTodoItemRequest request);

  @NotNull
  @Valid
  TodoItemResponse update(@NotNull @Valid final UpdateTodoItemRequest request);
}
