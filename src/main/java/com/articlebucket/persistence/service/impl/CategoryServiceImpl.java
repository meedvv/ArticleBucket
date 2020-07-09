package com.articlebucket.persistence.service.impl;

import com.articlebucket.persistence.model.exceptions.CategoryNotFoundException;
import com.articlebucket.persistence.model.entities.Category;
import com.articlebucket.persistence.repository.CategoryRepository;
import com.articlebucket.persistence.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findOneById(final Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException());
    }

    @Override
    public Category findOneByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).orElseThrow(() -> new CategoryNotFoundException());
    }

    @Override
    public Category create(final Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(final Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
