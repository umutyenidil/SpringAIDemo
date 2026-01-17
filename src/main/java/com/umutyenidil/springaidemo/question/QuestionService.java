package com.umutyenidil.springaidemo.question;

import com.umutyenidil.springaidemo.dto.response.SuccessResponse;
import com.umutyenidil.springaidemo.exception.InternalServerException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionMapper questionMapper;
    private final ChatClient chatClient;

    public QuestionService(
            QuestionMapper questionMapper,
            @Qualifier("ollamaChatClientBuilder") ChatClient.Builder chatClientBuilder
    ) {
        this.chatClient = chatClientBuilder.build();
        this.questionMapper = questionMapper;
    }

    public QuestionDTO generateQuestion(
            Optional<String> topic
    ) {
        var question = chatClient.prompt()
                .user("Ask me a random question. there must be options on the questions to choose")
                .call()
                .entity(QuestionSchema.class);

        if (question == null) throw new InternalServerException();

        return this.questionMapper.toQuestionDTO(question);
    }

}
