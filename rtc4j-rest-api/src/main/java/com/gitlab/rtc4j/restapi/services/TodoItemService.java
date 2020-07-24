package com.gitlab.rtc4j.restapi.services;

import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;

public interface TodoItemService extends CrudService<TodoItemRequest, TodoItemResponse> {
}
