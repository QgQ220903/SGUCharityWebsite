package com.web.sgucharitywebsite.service;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAllCategories();

    Category saveCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    CategoryDto findCategoryById(long categoryId);

    void deleteCategoryById(long categoryId);
}
