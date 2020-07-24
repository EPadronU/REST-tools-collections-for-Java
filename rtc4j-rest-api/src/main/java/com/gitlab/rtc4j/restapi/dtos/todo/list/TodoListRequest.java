package com.gitlab.rtc4j.restapi.dtos.todo.list;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TodoListRequest {

  @NotEmpty
  private String name;

  private String description;
}
