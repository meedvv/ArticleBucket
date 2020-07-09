package com.articlebucket.web.dto;

import lombok.Value;

import java.util.Date;

@Value
public class ArticleDto {

    private final Long id;

    private final String title;

    private final String logo;

    private final String description;

    private final String text;

    private final String categoryName;

    private final Date creationDate;

}
