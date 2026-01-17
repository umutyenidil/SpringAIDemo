package com.umutyenidil.springaidemo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AIConfig {

    @Bean
    @Primary
    public ChatClient.Builder chatClientBuilder(@Qualifier("openAiChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }

    @Bean
    @Primary
    public EmbeddingModel embeddingModel(@Qualifier("openAiEmbeddingModel") OpenAiEmbeddingModel embeddingModel) {
        return embeddingModel;
    }
}