package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.service.CategoryService;
import com.web.sgucharitywebsite.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.web.sgucharitywebsite.helper.ImgStorage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.Date;
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
    public String saveProject(@Valid @ModelAttribute("project") ProjectDto projectDto,
                              BindingResult result, Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("project", projectDto);
            return "admin/project/create";
        }

        // Xử lý ảnh tải lên
        MultipartFile image = projectDto.getThumbnailFile();
        if (image != null && !image.isEmpty()) {
            try {
                String imagePath = ImgStorage.saveImg(image); // Lưu ảnh và lấy đường dẫn
                projectDto.setThumbnail(imagePath); // Cập nhật đường dẫn vào DTO
            } catch (IOException ex) {
                model.addAttribute("error", "Failed to upload the file.");
                return "admin/project/create";
            }
        }

        projectService.createProject(projectDto); // Lưu dự án vào DB
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
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("project", projectDto);
            return "admin/project/update";
        }

        // Xử lý ảnh mới nếu được tải lên
        MultipartFile image = projectDto.getThumbnailFile();
        if (image != null && !image.isEmpty()) {
            try {
                String imagePath = ImgStorage.saveImg(image); // Lưu ảnh và lấy đường dẫn
                projectDto.setThumbnail(imagePath); // Cập nhật đường dẫn mới
            } catch (IOException ex) {
                model.addAttribute("error", "Failed to upload the file.");
                return "admin/project/update";
            }
        } else {
            // Nếu không tải ảnh mới, giữ nguyên đường dẫn ảnh cũ
            ProjectDto existingProject = projectService.findProjectById(projectId);
            projectDto.setThumbnail(existingProject.getThumbnail());
        }

        // Cập nhật dự án vào DB
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
