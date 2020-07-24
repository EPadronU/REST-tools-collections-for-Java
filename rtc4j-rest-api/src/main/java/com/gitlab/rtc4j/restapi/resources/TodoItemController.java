package com.gitlab.rtc4j.restapi.resources;

import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.services.TodoItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo-item")
public class TodoItemController extends CrudController<TodoItemRequest, TodoItemResponse> {

  public TodoItemController(final TodoItemService service) {
    super(service);
  }
}
