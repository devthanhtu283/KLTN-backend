package com.demo.services;

import com.demo.dtos.CategoryDTO;
import com.demo.dtos.WorktypeDTO;
import com.demo.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CategoryDTO> findAll() {
        return mapper.map(categoryRepository.findAll(), new TypeToken<List<CategoryDTO>>() {
        }.getType());
    }

    @Override
    public List<CategoryDTO> getSubcategoriesByCategoryName(String categoryName) {
        return mapper.map(categoryRepository.findByCategoryName(categoryName), new TypeToken<List<CategoryDTO>>() {
        }.getType());
    }
}
