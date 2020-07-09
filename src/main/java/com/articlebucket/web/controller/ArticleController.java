package com.articlebucket.web.controller;

import com.articlebucket.persistence.model.entities.Article;
import com.articlebucket.persistence.service.ArticleService;
import com.articlebucket.web.dto.ArticleDto;
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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        return ResponseEntity.status(OK).body(articleService.findAll());
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> getOneById(@PathVariable final Long articleId) {
        return ResponseEntity.status(OK).body(articleService.findOneById(articleId));
    }

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody final ArticleDto articleDto) {
        return ResponseEntity.status(CREATED).body(articleService.create(articleDto));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> delete(@PathVariable final Long articleId) {
        articleService.delete(articleId);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
