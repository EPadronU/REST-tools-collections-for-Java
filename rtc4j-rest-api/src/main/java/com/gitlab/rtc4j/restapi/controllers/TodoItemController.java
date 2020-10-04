package com.gitlab.rtc4j.restapi.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemRequest;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemTagsRequest;
import com.gitlab.rtc4j.restapi.services.TodoItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/todo-item")
public class TodoItemController extends CrudController<TodoItemRequest, TodoItemResponse> {

  private final TodoItemService service;

  public TodoItemController(final TodoItemService service) {
    super(service);

    this.service = service;
  }

  @PutMapping("/{id}/tags")
  public ResponseEntity<TodoItemResponse> updateTags(
    @PathVariable @Min(1L) long id,
    @NotNull @Valid @RequestBody TodoItemTagsRequest request) {
    return ResponseEntity.of(service.update(id, request));
  }
}
