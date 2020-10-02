package com.gitlab.rtc4j.restapi.dtos.todo.list;

import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TodoListResponse {

  @Min(1L)
  private long id;

  @NotEmpty
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Set<@NotNull @Min(1L) Long> items;
}
