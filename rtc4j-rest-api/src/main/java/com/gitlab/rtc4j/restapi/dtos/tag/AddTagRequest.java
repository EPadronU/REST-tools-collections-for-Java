package com.gitlab.rtc4j.restapi.dtos.tag;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AddTagRequest {

  @NotEmpty
  private String name;
}
