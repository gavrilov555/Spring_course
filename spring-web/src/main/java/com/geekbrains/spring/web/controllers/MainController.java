package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.Exception.ResourceNotFoundException;
import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.services.ProductService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    private ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById (@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(" Product not found id" + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change_cost")
    public void changeCost(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeCost(productId, delta);
    }
    @GetMapping("/products/cost_between")
    public List<Product> findStudentsByScoreBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "1000") Integer max) {
        return productService.findByScoreBetween(min, max);
    }

}
