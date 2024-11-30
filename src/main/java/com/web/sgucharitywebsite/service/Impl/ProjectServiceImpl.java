package com.web.sgucharitywebsite.service.Impl;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.entity.Project;
import com.web.sgucharitywebsite.repository.AppUserRepository;
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
    private AppUserRepository appUserRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(CategoryRepository categoryRepository, AppUserRepository appUserRepository,
                              ProjectRepository projectRepository) {
        this.categoryRepository = categoryRepository;
        this.appUserRepository = appUserRepository;
        this.projectRepository = projectRepository;

    }

    @Override
    public Project saveProject(ProjectDto projectDto) {

        return projectRepository.save(mapToProject(projectDto));
    }

    @Override
    public List<Object[]> getProjectsAndAmount() {
        return projectRepository.findAll()
                .stream()
                .map(project -> new Object[]{project.getName(), project.getCurrentAmount()})
                .toList();
    }

    @Override
    public void createProject(ProjectDto projectDto) {
        Category category = categoryRepository.findById(projectDto.getCategoryId()).get();
        AppUser user = appUserRepository.findById(projectDto.getUserId()).get();
        Project project = mapToProject(projectDto);
        project.setCategory(category);
        project.setUser(user);
        projectRepository.save(project);
    }

    @Override
    public List<ProjectDto> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map((project) -> mapToProjectDto(project)).collect(Collectors.toList());
    }

    @Override
    public void updateProject(ProjectDto projectDto) {
        Category category = categoryRepository.findById(projectDto.getCategoryId()).get();
        AppUser user = appUserRepository.findById(projectDto.getUserId()).get();
        Project project = mapToProject(projectDto);
        project.setCategory(category);
        project.setUser(user);
        projectRepository.save(project);
    }

    @Override
    public ProjectDto findProjectById(long projectId) {
        Project project = projectRepository.findById(projectId).get();
        return mapToProjectDto(project);
    }

    @Override
    public void deleteProjectById(long projectId) {
        projectRepository.deleteById(projectId);
    }

    private Project mapToProject(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .content(projectDto.getContent())
                .thumbnail(projectDto.getThumbnail())
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
                .thumbnail(project.getThumbnail())
                .createOn(project.getCreateOn())
                .updateOn(project.getUpdateOn())
                .categoryId(project.getCategory().getId())
                .userId(project.getUser().getId())
                .build();
    }
}