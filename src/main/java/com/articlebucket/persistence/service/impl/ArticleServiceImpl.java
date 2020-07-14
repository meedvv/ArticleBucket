package com.articlebucket.persistence.service.impl;

import com.articlebucket.persistence.model.entities.Category;
import com.articlebucket.persistence.model.exceptions.ArticleNotFoundException;
import com.articlebucket.persistence.model.entities.Article;
import com.articlebucket.persistence.repository.ArticleRepository;
import com.articlebucket.persistence.service.ArticleService;
import com.articlebucket.persistence.service.CategoryService;
import com.articlebucket.web.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final CategoryService categoryService;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findOneById(final Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException());
    }

    @Override
    public Article create(final ArticleDto articleDto) {

        final Category category = categoryService.findOneByName(articleDto.getCategoryName());

        final Article article = new Article();
        article.setContent(articleDto.getContent());
        article.setCategory(category);
        article.setCreationDate(articleDto.getCreationDate());
        article.setTitle(articleDto.getTitle());
        article.setLogo(articleDto.getLogo());

        return articleRepository.save(article);
    }

    @Override
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }

}
