package com.gitlab.rtc4j.restapi;

import java.util.List;
import java.util.Set;

import com.gitlab.rtc4j.restapi.daos.TagDAO;
import com.gitlab.rtc4j.restapi.daos.TodoItemDAO;
import com.gitlab.rtc4j.restapi.daos.TodoListDAO;
import com.gitlab.rtc4j.restapi.domain.Tag;
import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.domain.TodoList;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import com.gitlab.rtc4j.restapi.services.TodoListService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application implements ApplicationRunner {

  private final TodoListDAO todoListDAO;

  private final TodoItemDAO todoItemDAO;

  private final TagDAO tagDAO;

  private final TodoListService todoListService;

  public Application(
    final TodoListDAO todoListDAO,
    final TodoItemDAO todoItemDAO,
    final TagDAO tagDAO,
    final TodoListService todoListService) {
    this.todoListDAO = todoListDAO;
    this.todoItemDAO = todoItemDAO;
    this.tagDAO = tagDAO;
    this.todoListService = todoListService;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/")
  public ResponseEntity<List<TodoListResponse>> getAllTodoLists() {
    return ResponseEntity.ok(todoListService.findAll());
  }

  @Override
  public void run(final ApplicationArguments args) throws Exception {
    final Tag javaTag = tagDAO.save(Tag
      .builder()
      .name("Java")
      .build());

    final Tag developmentTag = tagDAO.save(Tag
      .builder()
      .name("Development")
      .tags(Set.of(javaTag))
      .build());

    final TodoList rtc4jList = todoListDAO.save(TodoList
      .builder()
      .name("RTC4J list")
      .description("The RTC4J project's list")
      .build());

    final TodoItem restfulApi = todoItemDAO.save(TodoItem
      .builder()
      .name("Create RESTful API")
      .description("The server")
      .list(rtc4jList)
      .tags(Set.of(javaTag))
      .build());

    todoItemDAO.save(TodoItem
      .builder()
      .name("Create the bases")
      .description("Initial development")
      .list(rtc4jList)
      .parent(restfulApi)
      .tags(Set.of(developmentTag))
      .build());

    todoItemDAO.save(TodoItem
      .builder()
      .name("Create HTML-based API")
      .description("Experiment with plain-old HTML")
      .list(rtc4jList)
      .parent(restfulApi)
      .tags(Set.of(developmentTag))
      .build());

    todoItemDAO.save(TodoItem
      .builder()
      .name("Create REST client")
      .description("The HATEOAS-aware client")
      .list(rtc4jList)
      .tags(Set.of(developmentTag))
      .build());
  }
}
