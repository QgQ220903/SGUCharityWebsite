package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.dto.BlogDto;
import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.repository.helper.ImgStorage;
import com.web.sgucharitywebsite.service.BlogService;
import com.web.sgucharitywebsite.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminBlogController {
    private BlogService blogService;
    private CategoryService categoryService;

    public AdminBlogController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }
    @RequestMapping("/blog")
    public String adminBlog(Model model) {
        List<BlogDto> blogDtoList = blogService.findAllBlogs();
        model.addAttribute("blogs", blogDtoList);
        return "admin/blog/index";
    }
    @GetMapping("/blog/create")
    public String createBlogForm(Model model) {
        BlogDto blogDto = new BlogDto();
        model.addAttribute("blog", blogDto);
        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDtoList);
        return "admin/blog/create";
    }

    @PostMapping("/blog/create")
    public String saveBlog(@Valid @ModelAttribute("blog") BlogDto blogDto, BindingResult result,
                              Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("blog", blogDto);
            return "admin/blog/create";
        }
        // Xử lý ảnh tải lên
        MultipartFile image = blogDto.getThumbnailFile();
        if (image != null && !image.isEmpty()) {
            try {
                String imagePath = ImgStorage.saveImg(image); // Lưu ảnh và lấy đường dẫn
                blogDto.setThumbnail(imagePath); // Cập nhật đường dẫn vào DTO
            } catch (IOException ex) {
                model.addAttribute("error", "Failed to upload the file.");
                return "admin/blog/create";
            }
        }
        blogService.createBlog(blogDto);
        redirectAttributes.addFlashAttribute("success", "Blog created successfully!");
        return "redirect:/admin/blog";
    }
    @GetMapping("/blog/update/{blogId}")
    public String updateBlog(@PathVariable("blogId") long blogId, Model model) {
        BlogDto blogDto = blogService.findBlogById(blogId);
        model.addAttribute("blog", blogDto);
        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDtoList);
        return "admin/blog/update";
    }

    @PostMapping("/blog/update/{blogId}")
    public String updateBlog(@PathVariable("blogId") long blogId,
                                @Valid @ModelAttribute("blog") BlogDto blogDto,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            model.addAttribute("blog", blogDto);
            return "admin/blog/update";
        }
        // Xử lý ảnh mới nếu được tải lên
        MultipartFile image = blogDto.getThumbnailFile();
        if (image != null && !image.isEmpty()) {
            try {
                String imagePath = ImgStorage.saveImg(image); // Lưu ảnh và lấy đường dẫn
                blogDto.setThumbnail(imagePath); // Cập nhật đường dẫn mới
            } catch (IOException ex) {
                model.addAttribute("error", "Failed to upload the file.");
                return "admin/blog/update";
            }
        } else {
            // Nếu không tải ảnh mới, giữ nguyên đường dẫn ảnh cũ
            BlogDto existingBlog = blogService.findBlogById(blogId);
            blogDto.setThumbnail(existingBlog.getThumbnail());
        }

        blogDto.setId(blogId);
        blogService.updateBlog(blogDto);
        return "redirect:/admin/blog";
    }
    @GetMapping("/blog/detail/{blogId}")
    public String detail(@PathVariable("blogId") long blogId, Model model) {
        BlogDto blogDto = blogService.findBlogById(blogId);
        model.addAttribute("blog", blogDto);
        return "admin/blog/detail";
    }
    @GetMapping("/blog/delete/{blogId}")
    public String delete(@PathVariable("blogId") long blogId, Model model) {
        blogService.deleteBlogById(blogId);
        return "redirect:/admin/blog";
    }
}
