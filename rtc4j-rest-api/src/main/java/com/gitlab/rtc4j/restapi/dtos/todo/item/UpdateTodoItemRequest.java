package com.gitlab.rtc4j.restapi.dtos.todo.item;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UpdateTodoItemRequest {

  @Min(1L)
  private long id;

  @NotEmpty
  private String name;

  private String description;

  @Min(1)
  @Max(Integer.MAX_VALUE)
  private int weight;

  @NotNull
  private TodoItem.Status status;

  @Min(1L)
  private Long listId;

  @Min(1L)
  private Long parentId;
}
