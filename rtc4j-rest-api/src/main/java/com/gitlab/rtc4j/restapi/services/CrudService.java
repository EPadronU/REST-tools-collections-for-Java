package com.gitlab.rtc4j.restapi.services;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface CrudService<Request, Response> {

  @NotNull
  List<@NotNull @Valid Response> findAll();

  @NotNull
  Optional<@Valid Response> findById(@Min(1L) final long id);

  @NotNull
  @Valid
  Response save(@NotNull @Valid final Request request);

  @NotNull
  @Valid
  Response update(
    @Min(1L) final long id,
    @NotNull @Valid final Request request);

  @NotNull
  @Valid
  void deleteById(@Min(1L) final long id);
}
