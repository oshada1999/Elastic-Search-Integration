package com.example.Elastic.Search.Integration.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.Elastic.Search.Integration.elastic.CategoryIndex;
import com.example.Elastic.Search.Integration.elastic.ProductIndex;
import com.example.Elastic.Search.Integration.entity.Category;
import com.example.Elastic.Search.Integration.entity.Product;
import com.example.Elastic.Search.Integration.repository.CategoryIndexRepository;
import com.example.Elastic.Search.Integration.repository.CategoryRepository;
import com.example.Elastic.Search.Integration.repository.ProductIndexRepository;
import com.example.Elastic.Search.Integration.repository.ProductRepository;
import com.example.Elastic.Search.Integration.util.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.suggest.Completion;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class SearchEngineService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductIndexRepository productIndexRepository;

    @Autowired
    private CategoryIndexRepository categoryIndexRepository;

    public void indexExistingDataCategory() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            CategoryIndex categoryIndex = new CategoryIndex();
            categoryIndex.setId(category.getId());
            categoryIndex.setName(category.getName());
            categoryIndex.setDescription(category.getDescription());

            Completion completion = new Completion(new String[]{category.getName()});
            categoryIndex.setNameSuggest(completion);

            categoryIndexRepository.save(categoryIndex);
        }
    }


    // Index all existing data from MySQL into Elasticsearch
    public void indexExistingData() {
        // Index all products
//        List<Product> products = productRepository.findAll();
//        for (Product product : products) {
            ProductIndex productIndex = new ProductIndex();
            productIndex.setId(1L);
            productIndex.setName("rice");
            productIndex.setDescription("rice rice");
            productIndex.setPrice(250.00);
            productIndexRepository.save(productIndex);
//        }

        // Index all categories
//        List<Category> categories = categoryRepository.findAll();
//        for (Category category : categories) {
//            CategoryIndex categoryIndex = new CategoryIndex();
//            categoryIndex.setId(category.getId());
//            categoryIndex.setName(category.getName());
//            categoryIndex.setDescription(category.getDescription());
//            categoryIndexRepository.save(categoryIndex);
//        }
    }

    // Method to search across products and categories
    public List<Object> search(String query) {
        List<Object> results = new ArrayList<>();

        // Search products in Elasticsearch
//        List<ProductIndex> productResults = productIndexRepository.getSuggestions(query);
//        results.addAll(productResults);

        // Search categories in Elasticsearch
        List<CategoryIndex> categoryResults = categoryIndexRepository.getSuggestions(query);
        results.addAll(categoryResults);

        return results;
    }

    public List<CategoryIndex> getSuggestions(String prefix) {
        return categoryIndexRepository.getSuggestions(prefix);
    }

}
