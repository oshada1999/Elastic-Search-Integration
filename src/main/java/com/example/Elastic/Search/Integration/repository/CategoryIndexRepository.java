package com.example.Elastic.Search.Integration.repository;

import com.example.Elastic.Search.Integration.elastic.CategoryIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CategoryIndexRepository extends ElasticsearchRepository<CategoryIndex, Long> {

    @Query("{\"suggest\": {\"category-suggest\": {\"prefix\": \"?0\", \"completion\": {\"field\": \"nameSuggest\", \"fuzzy\": {\"fuzziness\": \"AUTO\"}}}}}")
    List<CategoryIndex> getSuggestions(String prefix);
}
