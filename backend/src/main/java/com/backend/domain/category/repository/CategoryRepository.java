package com.backend.domain.category.repository;

import com.backend.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 카테고리 수정
//    Category updateCategory(Category category);
}
