package org.personal.projectjot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.personal.projectjot.entities.Project;
import org.personal.projectjot.repository.ProjectRepository;
import org.personal.projectjot.service.project.ProjectService;
import org.personal.projectjot.service.project.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectServiceImpl projectServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    private final String key = "&h3shDjqAo7FyG7BMW@QyUF9F8JyW@QZVQmjLd";

    @BeforeEach
    @Transactional
    void setUp() {
        projectRepository.save(
                Project.builder()
                        .title("Understanding Spring in-Depth")
                        .description("To really understand what Spring does under the hood is quite complex...")
                        .selfReferenceLink("https")
                        .thumbnailLink("https")
                        .build()
        );
    }

    @AfterEach
    void tearDown() {
        projectRepository.deleteAll();
        projectRepository.flush();
    }

    @Test
    void testFindProjectByTitle() throws Exception {
        String testTitle = "Understanding Spring in-Depth";

        mockMvc.perform(
                        get(ProjectController.PROJECT_PATH).param("projectTitle", testTitle)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("KEY", key))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(testTitle)));
    }


    @Test
    void testFindProjectById() throws Exception {
        Long testBlogId = projectRepository.findAll().get(0).getId();

        mockMvc.perform(
                        get(ProjectController.PROJECT_PATH + "/{id}", testBlogId)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("KEY", key))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBlogId.intValue())));
    }

    @Test
    void testFindAllProjects() throws Exception {

        mockMvc.perform(
                        get(ProjectController.PROJECT_PATH + "s")
                                .accept(MediaType.APPLICATION_JSON)
                                .header("KEY", key))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(1)));
    }


    @Test
    @Transactional
    void testCreateNewProject() throws Exception{
        Project testProject = Project.builder()
                .title("This is a test project")
                .description("I am currently testing the project controller save endpoint")
                .selfReferenceLink("https")
                .thumbnailLink("https")
                .build();

        mockMvc.perform(
                        post(ProjectController.PROJECT_PATH + "/new")
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testProject))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("KEY", key))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    void testUpdateProjectById() throws Exception {
        Project project = projectRepository.findAll().get(0);
        Project updatedProject = Project.builder()
                .title("THIS IS A TEST FOR UPDATING PROJECT TITLE")
                .description(project.getDescription())
                .thumbnailLink(project.getThumbnailLink())
                .selfReferenceLink(project.getSelfReferenceLink())
                .build();

        mockMvc.perform(
                        post(ProjectController.PROJECT_PATH + "/update/{id}", project.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updatedProject))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("KEY", key))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void testDeleteProjectById() throws Exception {
        Project project = projectRepository.findAll().get(0);

        mockMvc.perform(
                        delete(ProjectController.PROJECT_PATH + "/delete/{id}", project.getId())
                                .header("KEY", key))
                .andExpect(status().isAccepted());
    }
}
































