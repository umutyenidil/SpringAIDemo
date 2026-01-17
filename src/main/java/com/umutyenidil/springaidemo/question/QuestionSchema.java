package com.umutyenidil.springaidemo.question;

import lombok.Builder;

import java.util.List;

public record QuestionSchema(
        String questionText,
        List<OptionSchema> questionOptions
) {
    public record OptionSchema(
            String optionText
    ) {
    }
}
