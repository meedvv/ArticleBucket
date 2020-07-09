package com.articlebucket.web.controller.advice;

import com.articlebucket.persistence.model.exceptions.ArticleNotFoundException;
import com.articlebucket.persistence.model.exceptions.CategoryNotFoundException;
import com.articlebucket.web.dto.ErrorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ArticleNotFoundException.class, CategoryNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> onInvalidDate(final RuntimeException exception) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ErrorResponseDto(exception.getMessage(), NOT_FOUND));
    }

}
