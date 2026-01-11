package com.umutyenidil.springaidemo.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        chatClient = chatClientBuilder.build();
    }

    @GetMapping()
    public String chat() {
        return chatClient.prompt()
                .user("Tell me an interesting fact about the programming language named Java.")
                .call()
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> stream() {
        return chatClient.prompt()
                .user("I'm visiting Hilton Head soon, can you give me 10 places I must visit")
                .stream()
                .content();
    }
}
