package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminProjectController {

    private ProjectService projectService;

    public AdminProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping("/project")
    public String index() {
        return "admin/project/index";
    }

    @GetMapping("/project/create")
    public String projectCreate() {
        return "admin/project/create";
    }

    @PostMapping("/projects/create") // Map this method to a POST request to /admin/api/projects
    public ResponseEntity<String> createProject(@RequestBody ProjectDto projectDto, Long categoryId) {
        // Process the projectDto using projectService
        projectService.createProject(projectDto, categoryId);
        // Return a success response
        return new ResponseEntity<>("Project created successfully", HttpStatus.CREATED);

    }
}
