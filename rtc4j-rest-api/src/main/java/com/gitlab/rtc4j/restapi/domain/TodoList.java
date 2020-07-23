package com.gitlab.rtc4j.restapi.domain;

import java.util.Collections;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Builder(toBuilder = true)
@Entity
public class TodoList {

  private static final String SEQUENCE = "TODO_LIST_SEQUENCE";

  @Id
  @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
  private long id;

  @NotEmpty
  private String name;

  @NotNull
  private String description = "";

  @NotNull
  @OneToMany(mappedBy = "list")
  private Set<@Valid @NotNull TodoItem> items = Collections.emptySet();

  @SuppressWarnings("FieldMayBeFinal")
  public static class TodoListBuilder {

    private String description = "";

    private Set<TodoItem> items = Collections.emptySet();
  }
}
