package com.gitlab.rtc4j.restapi.controllers;

import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import com.gitlab.rtc4j.restapi.services.TodoListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo-list")
public class TodoListController extends CrudController<TodoListRequest, TodoListResponse> {

  public TodoListController(final TodoListService service) {
    super(service);
  }
}
