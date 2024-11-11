package com.web.sgucharitywebsite.service.Impl;

import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.entity.Project;
import com.web.sgucharitywebsite.repository.CategoryRepository;
import com.web.sgucharitywebsite.repository.ProjectRepository;
import com.web.sgucharitywebsite.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private CategoryRepository categoryRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(CategoryRepository categoryRepository, ProjectRepository projectRepository) {
        this.categoryRepository = categoryRepository;
        this.projectRepository = projectRepository;
    }
    @Override
    public void createProject(ProjectDto projectDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        Project project = mapToProject(projectDto);
        project.setCategory(category);
        projectRepository.save(project);
    }

    @Override
    public List<ProjectDto> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map((project) -> mapToProjectDto(project)).collect(Collectors.toList());
    }

    @Override
    public void updateProject(ProjectDto projectDto, Long categoryId) {

    }

    @Override
    public ProjectDto findProjectById(long projectId) {
        return null;
    }

    @Override
    public void deleteProjectById(long categoryId) {

    }

    private Project mapToProject(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .content(projectDto.getContent())
                .startTime(projectDto.getStartTime())
                .endTime(projectDto.getEndTime())
                .targetAmount(projectDto.getTargetAmount())
                .currentAmount(projectDto.getCurrentAmount())
                .status(projectDto.getStatus())
                .createOn(projectDto.getCreateOn())
                .updateOn(projectDto.getUpdateOn())
                .build();
    }

    private ProjectDto mapToProjectDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .content(project.getContent())
                .startTime(project.getStartTime())
                .endTime(project.getEndTime())
                .targetAmount(project.getTargetAmount())
                .currentAmount(project.getCurrentAmount())
                .status(project.getStatus())
                .createOn(project.getCreateOn())
                .updateOn(project.getUpdateOn())
                .build();
    }
}
