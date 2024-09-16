package com.example.Elastic.Search.Integration.repository;

import com.example.Elastic.Search.Integration.elastic.ProductIndex;
import com.example.Elastic.Search.Integration.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameStartingWithIgnoreCase(String name);
}
