package com.gitlab.rtc4j.restapi.dtos.tag;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UpdateTagRequest {

  @Min(1L)
  private long id;

  @NotEmpty
  private String name;
}
