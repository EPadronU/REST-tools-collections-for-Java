package com.gitlab.rtc4j.restapi.services;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.todo.list.AddTodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.list.UpdateTodoListRequest;

public interface TodoListService {

  @NotNull
  List<@NotNull @Valid TodoListResponse> findAll();

  @NotNull
  Optional<@Valid TodoListResponse> findById(@Min(1L) final long id);

  @NotNull
  @Valid
  TodoListResponse save(@NotNull @Valid final AddTodoListRequest request);

  @NotNull
  @Valid
  TodoListResponse update(
    @Min(1L) final long id,
    @NotNull @Valid final UpdateTodoListRequest request);

  @NotNull
  @Valid
  void deleteById(@Min(1L) final long id);
}
