package com.umutyenidil.springaidemo.question;

import com.umutyenidil.springaidemo.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/single")
    public ResponseEntity<SuccessResponse<QuestionDTO>> generateQuestion(
            @RequestParam Optional<String> topic
    ) {
        var result = this.questionService.generateQuestion(topic);

        return ResponseEntity.ok(SuccessResponse.of(result));
    }
}
