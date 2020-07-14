package com.articlebucket.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiValidationErrorDto extends ApiErrorDto {

    private List<SubErrorDto> subErrorDtoList;

    public ApiValidationErrorDto addSubErrors(List<FieldError> fieldErrors) {
        subErrorDtoList =  fieldErrors.stream()
                            .map(this::getSubErrorDto)
                            .collect(Collectors.toList());
        return this;
    }

    private SubErrorDto getSubErrorDto(FieldError fieldError) {
        return new SubErrorDto(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }


    public class SubErrorDto {

        private String field;

        private Object rejectedValue;

        private String message;

        public SubErrorDto() {

        }

        public SubErrorDto(String field, Object rejectedValue, String message) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }

        public void setRejectedValue(Object rejectedValue) {
            this.rejectedValue = rejectedValue;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
