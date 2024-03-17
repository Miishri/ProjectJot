package org.personal.projectjot.service.project;

import org.personal.projectjot.entities.Project;
import org.personal.projectjot.exception.ProjectNotFoundException;
import org.personal.projectjot.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

        Project savedProject = projectRepository.save(project);
        savedProject.setSelfReferenceLink("");
        return savedProject;
    }

    @Override
    public Project updateProject(Long id, Project project) {

        Project updatedProjects = projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);

        AtomicReference<Optional<Project>> foundProject = new AtomicReference<>(projectRepository.findById(id));

        foundProject.get().ifPresentOrElse(
                projectToBeUpdated -> {
                    projectToBeUpdated.setTitle(project.getTitle());
                    projectToBeUpdated.setDescription(project.getDescription());
                    projectToBeUpdated.setThumbnailLink(project.getThumbnailLink());
                    projectToBeUpdated.setSelfReferenceLink(project.getSelfReferenceLink());
                },
                ProjectNotFoundException::new
        );

        return projectRepository.save(updatedProjects);
    }

    @Override
    public Boolean deleteProject(Long id) {
        if (!projectRepository.existsById(id)) return false;

        projectRepository.deleteById(id);
        return true;
    }
}