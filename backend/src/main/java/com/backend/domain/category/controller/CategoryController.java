package com.backend.domain.category.controller;

import com.backend.domain.category.dto.request.CategoryRequest;
import com.backend.domain.category.dto.response.CategoryResponse;
import com.backend.domain.category.service.CategoryService;
import com.backend.global.response.GenericResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 전체 조회
    @GetMapping
    public GenericResponse<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> categorieList = categoryService.categoryList();
        return GenericResponse.of(true, HttpStatus.OK.value(), categorieList);
    }

    // 카테고리 추가 (관리자만 가능)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)     // 200번이 나와서 임시로 CREATED 설정
    public GenericResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
        return GenericResponse.of(true, HttpStatus.CREATED.value(), categoryResponse);
    }

    // 카테고리 수정 (관리자만 가능)
    @PatchMapping("/{id}")
    public GenericResponse<CategoryResponse> updateCategory(
            @RequestBody @Valid CategoryRequest categoryRequest, @PathVariable("id") Long id) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return GenericResponse.of(true, HttpStatus.OK.value(), categoryResponse);
    }
}
