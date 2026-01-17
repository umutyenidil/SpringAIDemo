package com.umutyenidil.springaidemo.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
public record ErrorDetail(
        ErrorType type,
        ErrorSubject subject,
        String message
) {
    public enum ErrorType {
        SERVER
    }

    public enum ErrorSubject {
        SERVER
    }
}