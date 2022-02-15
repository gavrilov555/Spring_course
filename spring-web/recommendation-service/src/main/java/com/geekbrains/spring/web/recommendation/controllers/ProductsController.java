package com.geekbrains.spring.web.recommendation.controllers;


import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.recommendation.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final StatisticService statisticService;

    @GetMapping("/purchased")
    public List<ProductDto>getFrequentlyPurchasedProducts(){
        return statisticService.getRecommendationThreeProducts();
    }

    @GetMapping("/folding")
    public List<ProductDto>getFrequentlyFoldingToCartProducts(){
        return statisticService.getAddToCartThreeProducts();
    }

}
