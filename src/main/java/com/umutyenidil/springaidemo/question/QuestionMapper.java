package com.umutyenidil.springaidemo.question;

import org.springframework.stereotype.Service;

@Service
public class QuestionMapper {

    public QuestionDTO toQuestionDTO(QuestionSchema questionSchema) {
        return QuestionDTO.builder()
                .text(questionSchema.questionText())
                .options(
                        questionSchema.questionOptions()
                                .stream()
                                .map(this::toOptionDTO)
                                .toList()
                )
                .build();
    }

    public QuestionDTO.OptionDTO toOptionDTO(QuestionSchema.OptionSchema optionSchema) {
        return QuestionDTO.OptionDTO.builder()
                .text(optionSchema.optionText())
                .build();
    }
}
