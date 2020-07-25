package com.gitlab.rtc4j.restapi.dtos.todo.item;

import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TodoItemTagsRequest {

  @NotNull
  private Set<@Min(1L) Long> tags;
}
