package com.example.Elastic.Search.Integration.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.Elastic.Search.Integration.elastic.CategoryIndex;
import com.example.Elastic.Search.Integration.elastic.ProductIndex;
import com.example.Elastic.Search.Integration.entity.Product;
import com.example.Elastic.Search.Integration.util.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/9/2024
 * @project : Elastic-Search-Integration
 */
@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<ProductIndex> fuzzySearch(String approximateProductName) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.createSupplierQuery(approximateProductName);

        SearchResponse<ProductIndex> response = elasticsearchClient
                .search(s->s.index("products").query(supplier.get()),ProductIndex.class );
        System.out.println("Elasticsearch supper fuzzy query "+ supplier.get().toString());
        return response;
    }

    public SearchResponse<ProductIndex> autoSuggestProduct(String name,Long id) throws IOException {

        Supplier<Query> supplier = ElasticSearchUtil.createSupplierAutoSuggest(name,id);
        SearchResponse<ProductIndex> searchResponse = elasticsearchClient
                .search(s->s.index("products").query(supplier.get()), ProductIndex.class);

        System.out.println("Elasticsearch auto suggestion query "+ supplier.get().toString());
        return searchResponse;
    }
    public SearchResponse<ProductIndex> boolQueryImpl(String productName, Integer price) throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplierQueryForBoolQuery(productName, price);
        SearchResponse<ProductIndex> searchResponse = elasticsearchClient.search(s->s.index("products").query(supplier.get()),ProductIndex.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        return searchResponse;
    }
    public List<Object> autoSuggestProductAndCategory(String name, Long id) throws IOException {

        Supplier<Query> supplier = ElasticSearchUtil.createSupplierAutoSuggest(name, id);

        // Search in the products index
//        SearchResponse<ProductIndex> productResponse = elasticsearchClient.search(
//                s -> s.index("products").query(supplier.get()), ProductIndex.class);

        // Search in the categories index
        SearchResponse<CategoryIndex> categoryResponse = elasticsearchClient.search(
                s -> s.index("categories").query(supplier.get()), CategoryIndex.class);

        // Combine results from both responses
        List<Object> suggestions = new ArrayList<>();
//        productResponse.hits().hits().forEach(hit -> suggestions.add(hit.source()));
        categoryResponse.hits().hits().forEach(hit -> suggestions.add(hit.source()));

        System.out.println("Elasticsearch auto suggestion query " + supplier.get().toString());
        return suggestions;
    }
}
