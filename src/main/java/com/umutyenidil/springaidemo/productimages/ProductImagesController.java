package com.umutyenidil.springaidemo.productimages;

import com.umutyenidil.springaidemo.dto.response.SuccessResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.metadata.OpenAiImageGenerationMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product-images")
public class ProductImagesController {

    private final ChatClient chatClient;
    @Value("classpath:/images/3b7eo93b7eo93b7e.png")
    Resource sampleProductImage;
    private final OpenAiImageModel imageModel;

    public ProductImagesController(
            @Qualifier("openAIChatClient") ChatClient chatClient,
            OpenAiImageModel imageModel
    ) {
        this.chatClient = chatClient;
        this.imageModel = imageModel;
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

    @GetMapping("/generate")
    public ResponseEntity<SuccessResponse<String>> generateRandomProductImage() {
        String imageDefinition = "A beautiful mountain with a river come from these mountains.";

        ImageOptions options = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1024)
                .height(1024)
                .quality("hd")
                .style("vivid")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(imageDefinition, options);
        ImageResponse response  = imageModel.call(imagePrompt);

        String url = response.getResult().getOutput().getUrl();

        return ResponseEntity.ok(SuccessResponse.of(url));
    }
}
