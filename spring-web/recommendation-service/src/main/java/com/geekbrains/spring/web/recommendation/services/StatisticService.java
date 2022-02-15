package com.geekbrains.spring.web.recommendation.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.spring.web.api.core.OrderDto;
import com.geekbrains.spring.web.api.core.OrderItemDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.recommendation.integrations.CartServiceIntegration;
import com.geekbrains.spring.web.recommendation.integrations.OrderServiceIntegration;
import com.geekbrains.spring.web.recommendation.integrations.ProductServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StatisticService {
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderServiceIntegration orderServiceIntegration;
    private final ProductServiceIntegration productServiceIntegration;

    public List<ProductDto> getRecommendationThreeProducts() {
        List list = orderServiceIntegration.getOrderByTimePeriod(LocalDateTime.now().minus(30, ChronoUnit.DAYS),
                LocalDateTime.now());

        List<OrderDto>orderDtoList = new ArrayList<>();

        for(Object order : list) {
            ObjectMapper objectMapper = new ObjectMapper();
            OrderDto orderDto = objectMapper.convertValue(order, OrderDto.class);
            orderDtoList.add(orderDto);
        }

        List<OrderItemDto>orderItemDtoList = orderDtoList.stream()
                .map(OrderDto::getItems).flatMap(List::stream).collect(Collectors.toList());

        Set<Long>productIdSet = orderItemDtoList.stream().map(OrderItemDto::getProductId).collect(Collectors.toSet());

        Map<Long, Integer> productMap = new HashMap();
        for (Long productId : productIdSet) {
            int productCount = 0;
            for (OrderItemDto orderItem : orderItemDtoList) {
                if (Objects.equals(orderItem.getProductId(), productId)) {
                    productCount = productCount + orderItem.getQuantity();
                }
            }
            productMap.put(productId, productCount);
        }

        return productMap.entrySet().stream().sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .limit(3).map(i -> productServiceIntegration.getProductById(i.getKey())).collect(Collectors.toList());
    }

    public List<ProductDto>getAddToCartThreeProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List list = productServiceIntegration.getAllProducts();
        for (Object product : list){
            ObjectMapper objectMapper = new ObjectMapper();
            ProductDto productDto = objectMapper.convertValue(product, ProductDto.class);
            productDtoList.add(productDto);
        }
        Map<Long, Integer> productMap = new HashMap<>();
        for (ProductDto product  : productDtoList) {
            Integer counter = cartServiceIntegration.getCounterByProductId(product.getId());
            if (counter > 0 ){
                productMap.put(product.getId(), counter);
            }
        }
        return productMap.entrySet().stream().sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .limit(3).map(i -> productServiceIntegration.getProductById(i.getKey())).collect(Collectors.toList());

    }

}
