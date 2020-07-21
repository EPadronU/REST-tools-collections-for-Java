package com.gitlab.rtc4j.restapi.daos;

import com.gitlab.rtc4j.restapi.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemDAO extends JpaRepository<TodoItem, Long> {
}
