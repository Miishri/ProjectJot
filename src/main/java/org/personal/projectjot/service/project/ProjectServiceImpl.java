package org.personal.projectjot.service.project;

import org.personal.projectjot.entities.Project;
import org.personal.projectjot.exception.ProjectNotFoundException;
import org.personal.projectjot.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project getProjectByTitle(String projectTitle) {
        return projectRepository
                .findByTitle(projectTitle)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with name " + projectTitle));
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository
                .findById(id)
                .orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {

        if (projectRepository.findByTitle(project.getTitle()).isPresent()) {
            throw new ProjectNotFoundException("Project already exists with this name");
        }

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, Project project) {

        Project updatedProjects = projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);

        updatedProjects.setTitle(project.getTitle());
        updatedProjects.setDescription(project.getDescription());
        updatedProjects.setThumbnailLink(project.getThumbnailLink());
        updatedProjects.setSelfReferenceLink(project.getSelfReferenceLink());

        return projectRepository.save(updatedProjects);
    }

    @Override
    public Boolean deleteProject(Long id) {
        if (!projectRepository.existsById(id)) return false;

        projectRepository.deleteById(id);
        return true;
    }
}