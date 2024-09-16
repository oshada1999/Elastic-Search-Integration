package com.example.Elastic.Search.Integration.repository;

import com.example.Elastic.Search.Integration.elastic.ProductIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductIndexRepository extends ElasticsearchRepository<ProductIndex, Long> {
//    @Query("{\"suggest\": {\"products-suggest\": {\"prefix\": \"?0\", \"completion\": {\"field\": \"name_suggest\", \"fuzzy\": {\"fuzziness\": \"AUTO\"}}}}}")
//    List<ProductIndex> getSuggestions(String prefix);

}
