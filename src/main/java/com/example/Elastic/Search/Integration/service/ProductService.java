package com.example.Elastic.Search.Integration.service;

import com.example.Elastic.Search.Integration.elastic.ProductIndex;
import com.example.Elastic.Search.Integration.entity.Product;
import com.example.Elastic.Search.Integration.repository.ProductIndexRepository;
import com.example.Elastic.Search.Integration.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/7/2024
 * @project : Elastic-Search-Integration
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductIndexRepository productIndexRepository;

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        ProductIndex productIndex = new ProductIndex();
        productIndex.setId(savedProduct.getId());
        productIndex.setName(savedProduct.getName());
        productIndex.setDescription(savedProduct.getDescription());
        productIndex.setPrice(savedProduct.getPrice());
        productIndexRepository.save(productIndex);
        return savedProduct;
    }
    public List<Product> getSuggestions(String name) {
        // Use the LIKE clause with % wildcard for partial matching
        return productRepository.findByNameStartingWithIgnoreCase(name);
    }

}
