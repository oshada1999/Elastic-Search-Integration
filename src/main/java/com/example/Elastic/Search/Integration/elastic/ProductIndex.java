package com.example.Elastic.Search.Integration.elastic;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/7/2024
 * @project : Elastic-Search-Integration
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductIndex {
    @Id
    private Long id;

    private String name;
    private String description;
    private Double price;

    // Getters and setters
}
