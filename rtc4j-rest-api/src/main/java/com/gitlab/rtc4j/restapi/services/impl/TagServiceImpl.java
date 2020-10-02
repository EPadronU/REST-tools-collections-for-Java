package com.gitlab.rtc4j.restapi.services.impl;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gitlab.rtc4j.restapi.daos.TagDAO;
import com.gitlab.rtc4j.restapi.dtos.tag.MetaTagTagsRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagRequest;
import com.gitlab.rtc4j.restapi.dtos.tag.TagResponse;
import com.gitlab.rtc4j.restapi.mappers.TagMapper;
import com.gitlab.rtc4j.restapi.services.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
public class TagServiceImpl implements TagService {

  private final TagDAO tagDAO;

  private final TagMapper tagMapper;

  public TagServiceImpl(
    final TagDAO tagDAO,
    final TagMapper tagMapper) {
    this.tagDAO = tagDAO;
    this.tagMapper = tagMapper;
  }

  @Override
  public @NotNull List<@NotNull @Valid TagResponse> findAll() {
    return tagDAO
      .findAll()
      .stream()
      .map(tagMapper::toTagResponse)
      .collect(toList());
  }

  @Override
  public @NotNull Optional<@Valid TagResponse> findById(@Min(1L) final long id) {
    return tagDAO
      .findById(id)
      .map(tagMapper::toTagResponse);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TagResponse save(final @NotNull @Valid TagRequest request) {
    return tagMapper
      .toTagResponse(tagDAO.save(tagMapper.toTag(request)));
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid TagResponse update(
    @Min(1L) final long id,
    final @NotNull @Valid TagRequest request) {
    return tagDAO
      .findById(id)
      .map(dbTag -> tagMapper.toTag(dbTag, request))
      .map(tagDAO::save)
      .map(tagMapper::toTagResponse)
      .orElseThrow();
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public @NotNull @Valid void deleteById(@Min(1L) final long id) {
    tagDAO.deleteById(id);
  }

  @Override
  public @NotNull @Valid TagResponse update(
    @Min(1L) final long id,
    @NotNull @Valid final MetaTagTagsRequest request) {
    return tagDAO
      .findById(id)
      .map(dbTag -> tagMapper.toTag(dbTag, request))
      .map(tagDAO::save)
      .map(tagMapper::toTagResponse)
      .orElseThrow();
  }
}
