package com.umutyenidil.springaidemo.tools.datetime;

import com.umutyenidil.springaidemo.dto.response.SuccessResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/datetime")
public class DateTimeController {

    private final ChatClient chatClient;

    public DateTimeController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/tools")
    public ResponseEntity<SuccessResponse<String>> tools() {
        var result = chatClient.prompt()
                .user("What is tomorrow's date?")
                .tools(new DateTimeTools())
                .call()
                .content();

        return ResponseEntity.ok(SuccessResponse.of(result));
    }
}
