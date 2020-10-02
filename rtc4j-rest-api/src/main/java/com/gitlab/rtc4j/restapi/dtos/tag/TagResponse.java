package com.gitlab.rtc4j.restapi.dtos.tag;

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
public class TagResponse {

  @Min(1L)
  private long id;

  @NotEmpty
  private String name;

  @NotNull
  private Set<Long> tags;

  @NotNull
  private Set<Long> metaTags;

  @NotNull
  private Set<Long> taggedItems;

  private boolean meta;
}
