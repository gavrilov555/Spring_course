package com.geekbrains.spring.web.controllers;


import com.geekbrains.spring.web.Exception.ResourceNotFoundException;
import com.geekbrains.spring.web.converter.ProductConverter;
import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.CartDto;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.services.CartService;
import com.geekbrains.spring.web.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartControllers {

    private final ProductService productService;
    private final CartService cartService;
    private final ProductConverter productConverter;

    @GetMapping
    public ArrayList<CartDto>getCartList() {
        return cartService.getProductInCarts();
    }

    @PostMapping()
    public CartDto addProductToCart(@RequestParam(value = "id") Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not " +
                "found, id: " + id));
        ProductDto productDto = productConverter.entityToDto(product);
        return cartService.addProduct(productDto);
    }

    @PutMapping
    public CartDto updateProductCount(@RequestParam(value = "id") Long id,
                                               @RequestParam(value = "count") Integer count) {
        productService.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return cartService.changeProductCount(id, count);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not " +
                "found, id: " + id));
        cartService.deleteById(id);
    }


}