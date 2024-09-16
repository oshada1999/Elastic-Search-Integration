package com.example.Elastic.Search.Integration.repository;

import com.example.Elastic.Search.Integration.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
