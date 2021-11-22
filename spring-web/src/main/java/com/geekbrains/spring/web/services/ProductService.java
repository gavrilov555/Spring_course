package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.repositories.ProductDaoImpl;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDaoImpl productDao;

    public ProductService (ProductDaoImpl productDao) {
        this.productDao = productDao;
    }


    public List<Product> findAll() {
        return productDao.findAll();
    }


    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    public void changeCost(Long id, Integer delta) {
        Product product = productDao.findById(id);
        int newCost = product.getCost();
        newCost = newCost + delta;
        if (newCost < 0) {
            newCost = 0;
        }
        product.setCost(newCost);
        productDao.saveOrUpdate(product);
    }
}
