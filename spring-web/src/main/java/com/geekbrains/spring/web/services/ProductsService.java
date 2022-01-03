package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import com.geekbrains.spring.web.repositories.specifications.ProductsSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Page<Product> find(Integer minCost, Integer maxCost, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductsSpecifications.costGreaterOrEqualsThan(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductsSpecifications.costLessThanOrEqualsThan(maxCost));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 5));
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

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукта, не надйен в базе, id: " + productDto.getId()));
        product.setCost(productDto.getCost());
        product.setTitle(productDto.getTitle());
        return product;
    }
}

