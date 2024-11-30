package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.repository.ProjectRepository;
import com.web.sgucharitywebsite.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChartRestController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectService projectService;

    // API trả về số lượng dự án theo thể loại
    @GetMapping("/api/projects-by-category")
    public List<Object[]> getProjectsByCategory() {
        return projectRepository.countProjectsByCategory(); // Trả về danh sách số lượng dự án theo thể loại
    }

    @GetMapping("/api/projects-amount")
    public List<Object[]> getProjectsAndAmount() {
        return projectService.getProjectsAndAmount();
    }
}
