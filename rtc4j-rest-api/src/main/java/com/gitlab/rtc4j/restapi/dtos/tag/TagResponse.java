package com.gitlab.rtc4j.restapi.dtos.tag;

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
public class TagResponse {

  @Min(1L)
  private long id;

  @NotEmpty
  private String name;

  @NotNull
  private Set<@Valid TagResponse> tags;

  @NotNull
  private Set<@Valid TagResponse> metaTags;

  @NotNull
  private Set<@Valid TodoItemResponse> taggedItems;

  private boolean isMeta;
}
