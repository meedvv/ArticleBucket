package com.articlebucket.persistence.model.exceptions;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException() {
    }

    public ArticleNotFoundException(String s) {
        super(s);
    }

}
