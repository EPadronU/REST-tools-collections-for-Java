package com.gitlab.rtc4j.restapi.mappers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemTagsRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(TodoItemMapperDecorator.class)
public interface TodoItemMapper extends BaseMapper {

  @NotNull
  @Valid
  @Mapping(target = "list", source = "list.id")
  @Mapping(target = "parent", source = "parent.id")
  TodoItemResponse toTodoItemResponse(@NotNull @Valid final TodoItem todoItem);

  @NotNull
  @Valid
  @Mapping(target = "list", source = "listId")
  @Mapping(target = "parent", source = "parentId")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "tags", ignore = true)
  @Mapping(target = "children", ignore = true)
  TodoItem toTodoItem(@NotNull @Valid final TodoItemRequest request);

  @NotNull
  @Valid
  @Mapping(target = "id", source = "todoItem.id")
  @Mapping(target = "name", source = "request.name")
  @Mapping(target = "description", source = "request.description")
  @Mapping(target = "list", source = "request.listId")
  @Mapping(target = "parent", source = "request.parentId")
  @Mapping(target = "children", ignore = true)
  @Mapping(target = "tags", source = "todoItem.tags")
  @Mapping(target = "weight", source = "request.weight")
  @Mapping(target = "status", source = "request.status")
  TodoItem toTodoItem(
    @NotNull @Valid final TodoItem todoItem,
    @NotNull @Valid final TodoItemRequest request);

  @NotNull
  @Valid
  @Mapping(target = ".", source = "todoItem")
  @Mapping(target = "tags", source = "request.tags")
  TodoItem toTodoItem(
    @NotNull @Valid final TodoItem todoItem,
    @NotNull @Valid final TodoItemTagsRequest request);
}
