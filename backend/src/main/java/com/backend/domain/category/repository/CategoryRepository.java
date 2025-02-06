package com.backend.domain.category.repository;

import com.backend.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 중복 검사
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
