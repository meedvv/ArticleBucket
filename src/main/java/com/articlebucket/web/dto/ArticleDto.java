package com.articlebucket.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Value
public class ArticleDto {

    private final Long id;

    @NotNull(message = "{article.title.required}")
    @Size(min = 5, max = 20, message = "{article.title.length}")
    private final String title;

    @NotNull(message = "{article.logo.required}")
    private final String logo;

    @NotNull(message = "{article.content.required}")
    @Size(min = 10, max = 1024, message = "{article.content.length}")
    private final String content;

    @NotNull(message = "{category.name.required}")
    private final String categoryName;

    @JsonProperty(value = "created_at")
    private final Date creationDate;

}
