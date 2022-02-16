package com.geekbrains.spring.web.recommendation.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public Integer getCounterByProductId(Long productId) {
        Integer counter =  cartServiceWebClient.get()
                .uri("/api/v1/cart/product_count/"+productId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        return counter;
    }
}
