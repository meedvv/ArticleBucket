package com.articlebucket.web.advice;

import com.articlebucket.persistence.model.exceptions.ArticleNotFoundException;
import com.articlebucket.persistence.model.exceptions.CategoryNotFoundException;
import com.articlebucket.web.dto.ApiErrorDto;
import com.articlebucket.web.dto.ApiValidationErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RequiredArgsConstructor
public class ArticleBucketExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        return buildResponseEntity(ApiValidationErrorDto.builder()
                .errorCode(BAD_REQUEST)
                .message(getMessageByKey("validation.failed"))
                .error(ex.getClass().getSimpleName())
                .build()
                .addSubErrors(ex.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler({ArticleNotFoundException.class, CategoryNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(final RuntimeException ex) {
        return buildResponseEntity(ApiErrorDto.builder()
                .errorCode(NOT_FOUND)
                .message(ex.getMessage())
                .error(ex.getClass().getSimpleName())
                .build());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(final Throwable throwable) {
        return buildResponseEntity(ApiErrorDto.builder()
                .error(throwable.getClass().getSimpleName())
                .message(throwable.getMessage())
                .errorCode(INTERNAL_SERVER_ERROR)
                .build());
    }

    private ResponseEntity<Object> buildResponseEntity(final ApiErrorDto apiErrorDto) {
        return new ResponseEntity<>(apiErrorDto, apiErrorDto.getErrorCode());
    }

    private String getMessageByKey(final String key){
        return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());
    }

}
