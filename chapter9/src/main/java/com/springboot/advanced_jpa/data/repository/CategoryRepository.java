package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
