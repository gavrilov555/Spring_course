package com.geekbrains.spring.web.cart.integrations;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductsServiceIntegration {
    private final WebClient coreServiceWebClient;

    public Optional<ProductDto> findById(Long id) {
        ProductDto productDto =
        coreServiceWebClient.get()
                .uri("/api/v1/products" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        return Optional.ofNullable(productDto);
    }
}



  /*  public Response findById(Long id) {
        try{
            Response productDto = restTemplate
                    .getForObject(productServiceUrl + "/api/v1/products/with_exception/" + id, Response.class);
            return  productDto;
        }catch (HttpServerErrorException e) {
            Response response = new Response();
            response.setCode(400);
            response.setMessage("Core service не работает");
            response.setSuccess(false);
            response.setProductDto(null);
            return response;
        }
    }

   */
