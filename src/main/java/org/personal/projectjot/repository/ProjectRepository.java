package org.personal.projectjot.repository;

import org.personal.projectjot.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByTitle(String blogTitle);
}
