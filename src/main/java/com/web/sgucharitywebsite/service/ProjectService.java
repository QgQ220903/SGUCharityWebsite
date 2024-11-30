package com.web.sgucharitywebsite.service;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.entity.Project;

import java.util.List;

public interface ProjectService {

    void createProject(ProjectDto projectDto);
    List<ProjectDto> findAllProjects();
    void updateProject(ProjectDto projectDto);
    ProjectDto findProjectById(long projectId);
    void deleteProjectById(long projectId);
    Project saveProject(ProjectDto projectDto);
    List<Object[]> getProjectsAndAmount();
}
