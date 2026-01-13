package com.umutyenidil.springaidemo.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder(builderMethodName = "successBuilder")
public class SuccessResponse<T> extends BaseResponse {
    private T data;

    public static <T> SuccessResponse<T> of(T data) {
        return SuccessResponse.<T>successBuilder()
                .success(true)
                .timestamp(Instant.now())
                .data(data)
                .build();
    }
}