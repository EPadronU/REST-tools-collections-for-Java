package com.gitlab.rtc4j.restapi.mappers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoList;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TodoListMapper extends BaseMapper {

  @NotNull
  @Valid
  TodoListResponse toTodoListResponse(@NotNull @Valid final TodoList todoList);

  @NotNull
  @Valid
  @Mapping(target = "id", ignore = true)
  TodoList toTodoList(@NotNull @Valid final TodoListRequest request);

  @NotNull
  @Valid
  @Mapping(target = "id", source = "todoList.id")
  @Mapping(target = "name", source = "request.name")
  @Mapping(target = "description", source = "request.description")
  TodoList toTodoList(
    @NotNull @Valid final TodoList todoList,
    @NotNull @Valid final TodoListRequest request);
}
