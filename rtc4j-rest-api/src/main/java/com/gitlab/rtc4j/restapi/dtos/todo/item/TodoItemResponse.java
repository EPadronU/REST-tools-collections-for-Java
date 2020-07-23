package com.gitlab.rtc4j.restapi.dtos.todo.item;

import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
  private Long list;

  @Valid
  private Long parent;

  @NotNull
  private Set<Long> children;

  @NotNull
  private Set<Long> tags;
}
