package com.umutyenidil.springaidemo.question;

import com.umutyenidil.springaidemo.exception.InternalServerException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
        String basePrompt = "Ask me a random question. there must be options on the questions to choose";

        String userPrompt = topic
                .map(t -> String.format("%s The question must be about %s.", basePrompt, t))
                .orElse(basePrompt);

        var question = chatClient.prompt()
                .user(userPrompt)
                .call()
                .entity(QuestionSchema.class);

        if (question == null) throw new InternalServerException();

        return this.questionMapper.toQuestionDTO(question);
    }

}
