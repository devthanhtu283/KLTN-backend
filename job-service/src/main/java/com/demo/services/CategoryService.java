package com.demo.services;

import com.demo.dtos.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> findAll();

    public List<CategoryDTO> getSubcategoriesByCategoryName(String categoryName);

}
