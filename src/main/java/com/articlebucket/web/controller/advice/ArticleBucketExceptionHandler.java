package com.articlebucket.web.controller.advice;

import com.articlebucket.web.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ArticleBucketExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(final Throwable throwable) {
        return buildResponseEntity(ErrorResponseDto.builder()
                .error(throwable.getClass().getSimpleName())
                .message(throwable.getMessage())
                .errorCode(INTERNAL_SERVER_ERROR)
                .build());
    }


    private ResponseEntity<Object> buildResponseEntity(ErrorResponseDto apiError) {
        return new ResponseEntity<>(apiError, apiError.getErrorCode());
    }

}
