package com.gitlab.rtc4j.restapi.dtos.todo.list;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UpdateTodoListRequest {

  @Min(1L)
  private long id;

  @NotEmpty
  private String name;

  private String description;
}
