package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.Exception.ResourceNotFoundException;
import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductsRepository productsRepository;

    public ProductService (ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    @Transactional
    public void changeCost(Long productId, Integer delta) {
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(" Unable to change product cost. Product not found, id: " + productId));
        product.setCost(product.getCost() + delta);
    }

   public List<Product> findByScoreBetween(Integer min, Integer max) {
        return productsRepository.findAllByScoreBetween(min, max);
   }
}
