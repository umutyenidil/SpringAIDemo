package com.umutyenidil.springaidemo.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Getter
@SuperBuilder(builderMethodName = "errorBuilder")
public class ErrorResponse extends BaseResponse {
    private List<ErrorDetail> errors;

    public static ErrorResponse of(List<ErrorDetail> errors) {
        return ErrorResponse.errorBuilder()
                .success(false)
                .timestamp(Instant.now())
                .errors(errors)
                .build();
    }

    public static ErrorResponse of(ErrorDetail error) {
        return of(List.of(error));
    }

    public static ErrorResponse of(ErrorDetail.ErrorType type, ErrorDetail.ErrorSubject subject, String message) {
        return of(ErrorDetail.builder()
                .type(type)
                .subject(subject)
                .message(message)
                .build());
    }
}