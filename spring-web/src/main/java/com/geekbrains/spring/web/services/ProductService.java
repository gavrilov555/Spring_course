package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductsRepository productsRepository;

    public ProductService (ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getAllProducts() {
        return productsRepository.getAllProducts();
    }

    public void deleteById (Long id) {
        productsRepository.deleteById(id);
    }

    public void changeCost(Long productId, Integer delta) {
        Product product = productsRepository.findById(productId);
        product.setCost(product.getCost() + delta);
    }
}
