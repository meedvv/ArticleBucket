package com.articlebucket.web.controller;

import com.articlebucket.persistence.model.entities.Category;
import com.articlebucket.persistence.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.status(OK).body(categoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getOneById(@PathVariable final Long categoryId) {
        return ResponseEntity.status(OK).body(categoryService.findOneById(categoryId));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody final Category category) {
        return ResponseEntity.status(CREATED).body(categoryService.create(category));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable final Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
