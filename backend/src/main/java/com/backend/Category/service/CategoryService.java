package com.backend.Category.service;

import com.backend.Category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

}
