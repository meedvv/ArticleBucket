package com.articlebucket.persistence.model.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String s) {
        super(s);
    }

}
