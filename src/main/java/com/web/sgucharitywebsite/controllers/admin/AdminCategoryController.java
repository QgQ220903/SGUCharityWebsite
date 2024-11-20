package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.service.CategoryService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminCategoryController {
    private CategoryService categoryService;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/admin/category")
    public String adminCategory(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDtoList);
        return "admin/category/index";
    }

    @GetMapping("/admin/category/create")
    public String createCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/create";
    }

    @PostMapping("/admin/category/create")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("category", categoryDto);
            return "admin/category/create";
        }
        categoryService.saveCategory(categoryDto);
        redirectAttributes.addFlashAttribute("success", "Category created successfully!");
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/update/{categoryId}")
    public String updateCategory(@PathVariable("categoryId") long categoryId, Model model) {
        CategoryDto categoryDto = categoryService.findCategoryById(categoryId);
        model.addAttribute("category", categoryDto);
        return "admin/category/update";
    }

    @PostMapping("/admin/category/update/{categoryId}")
    public String updateCategory(@PathVariable("categoryId") long categoryId,
            @Valid @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "admin/category/update";
        }
        categoryDto.setId(categoryId);
        categoryService.updateCategory(categoryDto);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/detail/{categoryId}")
    public String detail(@PathVariable("categoryId") long categoryId, Model model) {
        CategoryDto categoryDto = categoryService.findCategoryById(categoryId);
        model.addAttribute("category", categoryDto);
        return "admin/category/detail";
    }

    @GetMapping("/admin/category/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") long categoryId, Model model) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/admin/category";
    }



}
