package com.umutyenidil.springaidemo.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    public ChatClient openAIChatClient(OpenAiChatModel chatModel) {
        return ChatClient.create(chatModel);
    }
}
