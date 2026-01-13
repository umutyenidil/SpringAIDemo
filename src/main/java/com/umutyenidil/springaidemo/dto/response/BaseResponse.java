package com.umutyenidil.springaidemo.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
public class BaseResponse {
    private boolean success;
    private Instant timestamp;
}
