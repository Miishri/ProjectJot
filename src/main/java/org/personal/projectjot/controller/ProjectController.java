package org.personal.projectjot.controller;


import lombok.RequiredArgsConstructor;
import org.personal.projectjot.entities.Project;
import org.personal.projectjot.service.project.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    public static final String PROJECT_PATH = "/project";
    private final ProjectService projectService;

    @GetMapping(PROJECT_PATH)
    public ResponseEntity<Project> findProjectByTitle(@RequestParam String projectTitle) {

        return new ResponseEntity<>(
                projectService.getProjectByTitle(projectTitle),
                HttpStatus.FOUND
        );
    }

    @GetMapping(PROJECT_PATH + "/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable Long id) {

        return new ResponseEntity<>(
                projectService.getProjectById(id),
                HttpStatus.FOUND
        );
    }

    @GetMapping(PROJECT_PATH + "s")
    public ResponseEntity<List<Project>> findAllProjects() {

        return new ResponseEntity<>(
                projectService.getProjects(),
                HttpStatus.OK
        );
    }

    @PostMapping(PROJECT_PATH + "/new")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {

        return new ResponseEntity<>(
                projectService.createProject(project),
                HttpStatus.CREATED
        );
    }

    @PutMapping(PROJECT_PATH + "/update/{id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable Long id, @RequestBody Project project) {

        return new ResponseEntity<>(
                projectService.updateProject(id, project),
                HttpStatus.OK
        );
    }

    @DeleteMapping(PROJECT_PATH + "/delete/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {

        if (projectService.deleteProject(id)) {
            return new ResponseEntity<>(
                    HttpStatus.ACCEPTED
            );
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
