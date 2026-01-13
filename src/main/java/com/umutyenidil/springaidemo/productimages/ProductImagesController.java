package com.umutyenidil.springaidemo.productimages;

import com.umutyenidil.springaidemo.dto.response.SuccessResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-images")
public class ProductImagesController {

    private final ChatClient chatClient;
    @Value("classpath:/images/3b7eo93b7eo93b7e.png")
    Resource sampleProductImage;

    public ProductImagesController(@Qualifier("openAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/detect")
    public ResponseEntity<SuccessResponse<String>> detectProductImage() {
        final String result = chatClient.prompt()
                .user(p->{
                    p.text("Can you please describe what you see in the following image?");
                    p.media(MimeTypeUtils.IMAGE_PNG, sampleProductImage);
                })
                .call()
                .content();

        return ResponseEntity.ok(SuccessResponse.of(result));
    }

}
