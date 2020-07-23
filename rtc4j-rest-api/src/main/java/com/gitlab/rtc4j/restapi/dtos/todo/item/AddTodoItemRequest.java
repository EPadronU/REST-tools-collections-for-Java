package com.gitlab.rtc4j.restapi.dtos.todo.item;

import java.util.Optional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AddTodoItemRequest {

  @NotEmpty
  private String name;

  private String description;

  @Min(1)
  @Max(Integer.MAX_VALUE)
  private int weight;

  @NotNull
  private TodoItem.Status status;

  @Min(1)
  private Long listId;

  @Min(1)
  private Long parentId;

  public Optional<Long> getListId() {
    return Optional.of(listId);
  }

  public Optional<Long> getParentId() {
    return Optional.of(listId);
  }
}
