package com.articlebucket.persistence.service;

import com.articlebucket.persistence.model.entities.Article;
import com.articlebucket.web.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findAll();

    ArticleDto findOneById(Long articleId);

    ArticleDto create(ArticleDto articleDto);

    void delete(Long articleId);

}
