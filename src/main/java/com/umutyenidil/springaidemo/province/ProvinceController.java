package com.umutyenidil.springaidemo.province;

import com.umutyenidil.springaidemo.dto.response.SuccessResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v1/provinces")
public class ProvinceController {

    private final ChatClient chatClient;

    public ProvinceController(@Qualifier("openAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/most-crowded")
    public ResponseEntity<SuccessResponse<List<Province>>> getMostCrowdedProvinces(
            @RequestParam Optional<Integer> count
    ) {
        final Provinces result = chatClient.prompt()
                .user(String.format("Give me the most crowded %d provinces in turkey.", count.orElse(1)))
                .call()
                .entity(Provinces.class);

        return ResponseEntity.ok(SuccessResponse.of(result.provinces()));
    }
}
