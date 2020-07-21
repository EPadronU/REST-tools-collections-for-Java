package com.gitlab.rtc4j.restapi.daos;

import com.gitlab.rtc4j.restapi.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDAO extends JpaRepository<Tag, Long> {
}
