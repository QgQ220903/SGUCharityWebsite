package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.entity.Project;
import com.web.sgucharitywebsite.helper.CurrencyFormatter;
import com.web.sgucharitywebsite.service.CategoryService;
import com.web.sgucharitywebsite.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminProjectController {

    private ProjectService projectService;
    @Autowired
    private CategoryService categoryService;

    public AdminProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping("/project")
    public String adminProject(Model model) {
        List<ProjectDto> projectDtoList = projectService.findAllProjects();
        model.addAttribute("projects", projectDtoList);
        return "admin/project/index";
    }

    @GetMapping("/project/create")
    public String createProjectForm(Model model) {
        ProjectDto projectDto = new ProjectDto();
        model.addAttribute("project", projectDto);
        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDtoList);
        return "admin/project/create";
    }

    @PostMapping("/project/create")
    public String saveProject(@Valid @ModelAttribute("project") ProjectDto projectDto, BindingResult result,
                               Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("project", projectDto);
            return "admin/project/create";
        }

        projectService.createProject(projectDto);
        redirectAttributes.addFlashAttribute("success", "Project created successfully!");
        return "redirect:/admin/project";
    }

    @GetMapping("/project/update/{projectId}")
    public String updateProject(@PathVariable("projectId") long projectId, Model model) {
        ProjectDto projectDto = projectService.findProjectById(projectId);
        model.addAttribute("project", projectDto);
        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDtoList);
        DecimalFormat df = new DecimalFormat("0");
        String formattedTargetAmount = df.format(projectDto.getTargetAmount());
        model.addAttribute("formattedTargetAmount", formattedTargetAmount);
        return "admin/project/update";
    }

    @PostMapping("/project/update/{projectId}")
    public String updateProject(@PathVariable("projectId") long projectId,
                                 @Valid @ModelAttribute("project") ProjectDto projectDto,
                                 BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "admin/project/update";
        }

        projectDto.setId(projectId);
        projectService.updateProject(projectDto);
        return "redirect:/admin/project";
    }

    @GetMapping("/project/detail/{projectId}")
    public String detail(@PathVariable("projectId") long projectId, Model model) {
        ProjectDto projectDto = projectService.findProjectById(projectId);
        model.addAttribute("project", projectDto);
        return "admin/project/detail";
    }

    @GetMapping("/project/delete/{projectId}")
    public String delete(@PathVariable("projectId") long projectId, Model model) {
        projectService.deleteProjectById(projectId);
        return "redirect:/admin/project";
    }
}
