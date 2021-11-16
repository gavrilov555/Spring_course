package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    ProductsRepository productsRepository;
@Autowired
    public MainController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/add")
    @ResponseBody
    public int add(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }


    @GetMapping("/product/{id}/info")
    @ResponseBody
    public int findProduct (@PathVariable int id) {
    return id;
    }

    @GetMapping("/products")
    public String showProductPage(Model model) {
        model.addAttribute("products", productsRepository.getAllProducts());
        return "products_page";
    }

    @GetMapping("/product/{id}")
    public String findProduct(Model model, @PathVariable Long id) {
        Product product = productsRepository.findById(id);
        model.addAttribute("product", product);
        return "product_page";
    }
}
