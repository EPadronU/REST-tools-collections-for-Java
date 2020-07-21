package com.gitlab.rtc4j.restapi.dtos.todo.item;

import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import com.gitlab.rtc4j.restapi.dtos.todo.list.TodoListResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TodoItemResponse {

  @Min(1L)
  private long id;

  @NotEmpty
  private String name;

  @NotNull
  private String description;

  @Min(1)
  private int weight;

  @NotNull
  private TodoItem.Status status;

  @NotNull
  @Valid
  private TodoListResponse list;

  @Valid
  private TodoItemResponse parent;

  @NotNull
  private Set<@Valid TodoItemResponse> children;

  @NotNull
  private Set<@Valid TagResponse> tags;
}
