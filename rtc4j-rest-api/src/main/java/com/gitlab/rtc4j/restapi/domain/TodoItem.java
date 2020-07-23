package com.gitlab.rtc4j.restapi.domain;

import java.util.Collections;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@Builder(toBuilder = true)
@Entity
public class TodoItem {

  private static final String SEQUENCE = "TODO_ITEM_SEQUENCE";

  @Id
  @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
  private long id;

  @NotEmpty
  private String name;

  @NotNull
  private String description = "";

  @Min(1)
  @Max(Integer.MAX_VALUE)
  private int weight = 1;

  @NotNull
  private Status status = Status.PENDING;

  @NotNull
  @Valid
  @ManyToOne
  @JoinColumn(name = "list_id")
  private TodoList list;

  @Valid
  @ManyToOne
  @JoinColumn(name = "parent_id")
  private TodoItem parent;

  @NotNull
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  private Set<@NotNull @Valid TodoItem> children = Collections.emptySet();

  @NotNull
  @ManyToMany
  @JoinTable(
    name = "TodoItemsTags",
    joinColumns = @JoinColumn(name = "item_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<@NotNull @Valid Tag> tags = Collections.emptySet();

  public enum Status {
    PENDING,
    DONE
  }

  @SuppressWarnings("FieldMayBeFinal")
  public static class TodoItemBuilder {

    private Status status = Status.PENDING;

    private Set<TodoItem> children = Collections.emptySet();

    private Set<Tag> tags = Collections.emptySet();

    private String description = "";

    private int weight = 1;
  }
}
