package com.example.Elastic.Search.Integration.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.Elastic.Search.Integration.elastic.CategoryIndex;
import com.example.Elastic.Search.Integration.elastic.ProductIndex;
import com.example.Elastic.Search.Integration.entity.Product;
import com.example.Elastic.Search.Integration.service.ElasticSearchService;
import com.example.Elastic.Search.Integration.service.SearchEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/8/2024
 * @project : Elastic-Search-Integration
 */
@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchEngineController {

    @Autowired
    private SearchEngineService searchEngineService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping
    public List<Object> search(@RequestParam String query) {
        return searchEngineService.search(query);
    }
    @GetMapping("/reindex")
    public String reindex() {
        searchEngineService.indexExistingData();
        return "Reindexing complete";
    }

    @GetMapping("/reindexCategory")
    public String reindexCategory() {
        searchEngineService.indexExistingDataCategory();
        return "Reindexing complete";
    }

    @GetMapping("/fuzzySearch/{approximateProductName}")
    List<ProductIndex> fuzzySearch( @PathVariable String approximateProductName ) throws IOException {
        SearchResponse<ProductIndex> searchResponse = elasticSearchService.fuzzySearch(approximateProductName);
        List<Hit<ProductIndex>> hitList = searchResponse.hits().hits();
        System.out.println(hitList);
        List<ProductIndex> productList = new ArrayList<>();
        for(Hit<ProductIndex> hit :hitList){
            productList.add(hit.source());
        }
        return productList;
    }

//    @GetMapping("/autoSuggest/{name}")
//    List<String> autoSuggestProductName(@PathVariable String name) throws IOException {
//        SearchResponse<ProductIndex> searchResponse = elasticSearchService.autoSuggestProduct(name);
//        List<Hit<ProductIndex>> hitList = searchResponse.hits().hits();
//        List<ProductIndex> productIndexList = new ArrayList<>();
//
//        for (Hit<ProductIndex> hit : hitList){
//            productIndexList.add(hit.source());
//        }
//
//        List<String> listOfNames = new ArrayList<>();
//        for (ProductIndex productIndex : productIndexList){
//            listOfNames.add(productIndex.getName());
//        }
//
//        return listOfNames;
//    }

    @GetMapping("/suggest")
    public List<CategoryIndex> suggest(@RequestParam String query) throws IOException {
        System.out.println("awaaaaaaaaaaaaaaaa" +query);
        return searchEngineService.getSuggestions(query);
    }

    @GetMapping("/boolQuery/{productName}/{price}")
    public List<ProductIndex> boolQuery(@PathVariable String productName, @PathVariable Integer price) throws IOException {

        SearchResponse<ProductIndex> searchResponse =  elasticSearchService.boolQueryImpl(productName,price);
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<ProductIndex>> listOfHits= searchResponse.hits().hits();
        List<ProductIndex> listOfProducts  = new ArrayList<>();
        for(Hit<ProductIndex> hit : listOfHits){
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }
    @GetMapping("/products-and-categories/suggestions/{name}/{id}")
    public List<Object> getSuggestions(@PathVariable String name, @PathVariable Long id) {
        try {
            return elasticSearchService.autoSuggestProductAndCategory(name, id);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
