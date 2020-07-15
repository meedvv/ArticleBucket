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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final CategoryService categoryService;

    @Override
    public List<ArticleDto> findAll() {

        return articleRepository.findAll().stream().map(this::getArticleDtoFrom).collect(Collectors.toList());
    }

    @Override
    public ArticleDto findOneById(final Long articleId) {
        final Article article = articleRepository.findById(articleId)
                                                    .orElseThrow(ArticleNotFoundException::new);
        return this.getArticleDtoFrom(article);
    }

    @Override
    public ArticleDto create(final ArticleDto articleDto) {
        final Article article = articleRepository.save(this.getArticleFrom(articleDto));
        return this.getArticleDtoFrom(article);
    }

    @Override
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    private ArticleDto getArticleDtoFrom(final Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .categoryName(article.getCategory().getName())
                .creationDate(article.getCreationDate())
                .logo(article.getLogo())
                .build();
    }

    private Article getArticleFrom(final ArticleDto articleDto) {
        final Category category = categoryService.findOneByName(articleDto.getCategoryName());
        final Article article = new Article();

        article.setContent(articleDto.getContent());
        article.setCategory(category);
        article.setCreationDate(articleDto.getCreationDate());
        article.setTitle(articleDto.getTitle());
        article.setLogo(articleDto.getLogo());

        return article;
    }

}
