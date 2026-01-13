package com.umutyenidil.springaidemo.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDetail {
    private String type;
    private String subject;
    private String message;
}