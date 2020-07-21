package com.gitlab.rtc4j.restapi.domain;

import java.util.Collections;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Tag {

  private static final String SEQUENCE = "TAG_SEQUENCE";

  @Id
  @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
  private long id;

  @NotEmpty
  private String name;

  @NotNull
  @ManyToMany
  @JoinTable(
    name = "MetaTags",
    joinColumns = @JoinColumn(name = "parent_id"),
    inverseJoinColumns = @JoinColumn(name = "child_id"))
  private Set<@NotNull @Valid Tag> metaTags = Collections.emptySet();

  @NotNull
  @ManyToMany(mappedBy = "metaTags")
  private Set<@NotNull @Valid Tag> tags = Collections.emptySet();

  @NotNull
  @ManyToMany(mappedBy = "tags")
  private Set<@NotNull @Valid TodoItem> taggedItems = Collections.emptySet();

  @SuppressWarnings("FieldMayBeFinal")
  public static class TagBuilder {

    private Set<Tag> tags = Collections.emptySet();

    private Set<Tag> metaTags = Collections.emptySet();

    private Set<TodoItem> taggedItems = Collections.emptySet();
  }
}
