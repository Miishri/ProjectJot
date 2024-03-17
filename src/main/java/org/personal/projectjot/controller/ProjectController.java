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
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Project> findProjectByTitle(@RequestParam String projectTitle) {

        return new ResponseEntity<>(
                projectService.getProjectByTitle(projectTitle),
                HttpStatus.FOUND
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable Long id) {

        return new ResponseEntity<>(
                projectService.getProjectById(id),
                HttpStatus.FOUND
        );
    }

    @GetMapping( "/all")
    public ResponseEntity<List<Project>> findAllProjects() {

        return new ResponseEntity<>(
                projectService.getProjects(),
                HttpStatus.OK
        );
    }

    @PostMapping("/new")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {

        return new ResponseEntity<>(
                projectService.createProject(project),
                HttpStatus.CREATED
        );
    }

    @PutMapping( "/update/{id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable Long id, @RequestBody Project project) {

        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {

        if (projectService.deleteProject(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
