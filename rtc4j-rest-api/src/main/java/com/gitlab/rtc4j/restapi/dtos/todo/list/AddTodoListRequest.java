package com.gitlab.rtc4j.restapi.dtos.todo.list;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AddTodoListRequest {

  @NotEmpty
  private String name;

  private String description;
}
