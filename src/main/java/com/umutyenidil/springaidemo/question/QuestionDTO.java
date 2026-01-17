package com.umutyenidil.springaidemo.question;

import lombok.Builder;

import java.util.List;

@Builder
public record QuestionDTO(
        String text,
        List<OptionDTO> options
) {
    @Builder
    public record OptionDTO(
            String text
    ) {
    }
}
