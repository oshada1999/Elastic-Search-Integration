package com.example.Elastic.Search.Integration.service;

import com.example.Elastic.Search.Integration.elastic.CategoryIndex;
import com.example.Elastic.Search.Integration.entity.Category;
import com.example.Elastic.Search.Integration.repository.CategoryIndexRepository;
import com.example.Elastic.Search.Integration.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/8/2024
 * @project : Elastic-Search-Integration
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryIndexRepository categoryIndexRepository;

    public Category saveCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        CategoryIndex categoryIndex = new CategoryIndex();
        categoryIndex.setId(savedCategory.getId());
        categoryIndex.setName(savedCategory.getName());
        categoryIndex.setDescription(savedCategory.getDescription());
        categoryIndexRepository.save(categoryIndex);
        return savedCategory;
    }
    public List<CategoryIndex> searchCategories(String query) {
        return categoryIndexRepository.getSuggestions(query);
    }
}
