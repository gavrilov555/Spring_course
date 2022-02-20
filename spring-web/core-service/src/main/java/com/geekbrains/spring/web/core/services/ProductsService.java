package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.exception.ResourceNotFoundException;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.response.Response;
import com.geekbrains.spring.web.core.converters.ProductConverter;
import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.repositories.ProductsRepository;
import com.geekbrains.spring.web.core.repositories.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductConverter productConverter;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));
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
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }

    public Response findByIdResponse (Long id) {
        try {
            Response response = new Response();
            response.setCode(200);
            response.setProductDto(productConverter.entityToDto(productsRepository.findById(id).get()));
            response.setSuccess(true);
            response.setMessage(" ");
            return response;
        } catch (NoSuchElementException e) {
            Response response = new Response();
            response.setCode(404);
            ;
            response.setSuccess(false);
            response.setMessage(" Невозможно обновить продукт");
            response.setProductDto(null);
            return response;
        }
    }

    public List<Product> findAll(){
        return productsRepository.findAll();
    }
}
