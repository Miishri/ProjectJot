package org.personal.projectjot.service.project;

import org.personal.projectjot.entities.Project;

import java.util.List;

public interface ProjectService {
    Project getProjectByTitle(String blogTitle);
    Project getProjectById(Long id);
    List<Project> getProjects();
    Project createProject(Project project);
    Project updateProject(Long id, Project project);

    Boolean deleteProject(Long id);
}
