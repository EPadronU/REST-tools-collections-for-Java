package com.gitlab.rtc4j.restapi.services;

import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemTagsRequest;

public interface TodoItemService extends CrudService<TodoItemRequest, TodoItemResponse> {

  @NotNull
  Optional<@Valid TodoItemResponse> update(
    @Min(1L) final long id,
    @NotNull @Valid final TodoItemTagsRequest request);
}
