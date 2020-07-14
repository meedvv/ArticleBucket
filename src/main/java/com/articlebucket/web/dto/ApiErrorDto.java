package com.articlebucket.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorDto {

    private String error;

    private String message;

    private HttpStatus errorCode;

}
