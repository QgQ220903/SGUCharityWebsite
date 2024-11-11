package com.web.sgucharitywebsite.service.Impl;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.repository.CategoryRepository;
import com.web.sgucharitywebsite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> mapToCategoryDto(category)).collect(Collectors.toList());
    }

    @Override
    public Category saveCategory(CategoryDto categoryDto) {
        return categoryRepository.save(mapToCategory(categoryDto));
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = mapToCategory(categoryDto);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto findCategoryById(long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return mapToCategoryDto(category);
    }

    @Override
    public void deleteCategoryById(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createOn(category.getCreateOn())
                .updatedOn(category.getUpdatedOn())
                .build();
        return categoryDto;
    }

    public Category mapToCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .createOn(categoryDto.getCreateOn())
                .updatedOn(categoryDto.getUpdatedOn())
                .build();
        return category;
    }

}
