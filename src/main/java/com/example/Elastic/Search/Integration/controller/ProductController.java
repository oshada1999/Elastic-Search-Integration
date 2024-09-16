package com.example.Elastic.Search.Integration.controller;

import com.example.Elastic.Search.Integration.elastic.ProductIndex;
import com.example.Elastic.Search.Integration.entity.Product;
import com.example.Elastic.Search.Integration.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/7/2024
 * @project : Elastic-Search-Integration
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
    @GetMapping("/suggestions")
    public List<Product> getSuggestions(@RequestParam String name) {
        System.out.println(name);
        return productService.getSuggestions(name);
    }
}
