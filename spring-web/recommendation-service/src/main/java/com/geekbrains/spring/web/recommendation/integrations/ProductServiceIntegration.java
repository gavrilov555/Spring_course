package com.geekbrains.spring.web.recommendation.integrations;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient coreServiceWebClient;

    public ProductDto getProductById(Long productId) {
        ProductDto productDto = coreServiceWebClient.get()
                .uri("/api/v1/products/"+productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        return productDto;
    }
    public List getAllProducts() {
        List productDtolist = coreServiceWebClient.get()
                .uri("/api/v1/products/products")
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return productDtolist;
    }
}
