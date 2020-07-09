package com.articlebucket.persistence.service;

import com.articlebucket.persistence.model.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findOneById(Long categoryId);

    Category findOneByName(String name);

    Category create(Category category);

    void delete(Long categoryId);

}
