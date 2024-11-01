package com.cr1stal.eventservice.mapper;

import com.cr1stal.eventservice.DTO.CategoryDto;
import com.cr1stal.eventservice.model.Category;

public class CategoryMapper {
    public static CategoryDto mapToCategoryDto(Category category, CategoryDto categoryDto){
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }
    public static Category mapToCategory(CategoryDto categoryDto, Category category){
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
