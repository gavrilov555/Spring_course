package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.data.Product;

import java.util.List;

public interface ProductDao {

    Product findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    Product saveOrUpdate(Product product);
}
