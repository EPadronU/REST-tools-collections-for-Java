package com.gitlab.rtc4j.restapi.dtos.tag;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateTagRequest {

  @NotEmpty
  private String name;
}
