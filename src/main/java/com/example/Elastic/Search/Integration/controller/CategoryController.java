package com.example.Elastic.Search.Integration.controller;

import com.example.Elastic.Search.Integration.elastic.CategoryIndex;
import com.example.Elastic.Search.Integration.entity.Category;
import com.example.Elastic.Search.Integration.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/8/2024
 * @project : Elastic-Search-Integration
 */
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/search")
    public List<CategoryIndex> searchCategories(@RequestParam String query) {
        return categoryService.searchCategories(query);
    }
}
