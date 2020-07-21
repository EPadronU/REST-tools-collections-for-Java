package com.gitlab.rtc4j.restapi.dtos.todo.list;

import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.dtos.todo.item.TodoItemResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TodoListResponse {

  @Min(1L)
  private long id;

  @NotEmpty
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Set<@Valid TodoItemResponse> items;
}
