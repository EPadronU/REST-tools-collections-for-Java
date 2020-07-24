package com.gitlab.rtc4j.restapi.services;

import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;

public interface TodoListService extends CrudService<TodoListRequest, TodoListResponse> {
}
