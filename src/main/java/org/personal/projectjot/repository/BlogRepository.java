package org.personal.projectjot.repository;

import org.personal.projectjot.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findByTitle(String blogTitle);
}
