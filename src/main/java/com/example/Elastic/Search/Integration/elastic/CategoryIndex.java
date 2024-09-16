package com.example.Elastic.Search.Integration.elastic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.suggest.Completion;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/8/2024
 * @project : Elastic-Search-Integration
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "categories")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryIndex {

    @Id
    private Long id;
    private String name;
    private String description;

    @CompletionField
    private Completion nameSuggest;
}
