package com.geekbrains.spring.web.services;
import com.geekbrains.spring.web.dto.CartDto;
import com.geekbrains.spring.web.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Getter
@Setter
@Slf4j
@Service
public class CartService {

    private ArrayList<CartDto> productInCarts = new ArrayList<>();

    public void deleteById(Long id) {
     ArrayList<CartDto> productInCartsForIteration = new ArrayList<>(productInCarts);
     for (CartDto product : productInCartsForIteration) {
         if (product.getId().equals(id)) {
             productInCarts.remove(product);
         }
     }
}
 public CartDto addProduct (ProductDto productDto) {
    boolean isPresent = false;
    CartDto cartDto = new CartDto(
        productDto.getId(),
        productDto.getTitle(),
        productDto.getCost(),
        1);
    for (CartDto product : productInCarts) {
        if (product.getId().equals(productDto.getId())) {
            isPresent = true;
            break;
        }
    }
     if (!isPresent) {
         productInCarts.add(cartDto);
     }
     return cartDto;
 }


    public CartDto changeProductCount(Long id, Integer count) {
        ArrayList<CartDto> productInCardsForIteration = new ArrayList<>(productInCarts);
        Integer newCount;
        CartDto newNewCartDto = null;
        for (CartDto product : productInCardsForIteration) {
            if (product.getId().equals(id)) {
                newCount = product.getCount() + count;
                if (newCount<1){
                    newCount=1;
                }
                CartDto newCartDto = new CartDto(
                        product.getId(),
                        product.getTitle(),
                        product.getCost(),
                        newCount);
                newNewCartDto = newCartDto;
                productInCarts.remove(product);
                productInCarts.add(newCartDto);
                break;
            }
        }
        return newNewCartDto;
    }
}

