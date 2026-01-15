package com.umutyenidil.springaidemo.product;

import com.umutyenidil.springaidemo.dto.response.SuccessResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ChatClient chatClient;

    public ProductController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(
                        QuestionAnswerAdvisor.builder(vectorStore)
                                .build()
                )
                .build();
    }

    @GetMapping("/rag")
    public ResponseEntity<SuccessResponse<Products>> getProductsRag(
    ) {
        var products = chatClient.prompt()
                .user("Give me a list of all the electronic products.")
                .call()
                .entity(Products.class);

        return ResponseEntity.ok(SuccessResponse.of(products));
    }
}
