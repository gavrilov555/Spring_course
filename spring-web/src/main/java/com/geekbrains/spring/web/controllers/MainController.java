package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.Exception.ResourceNotFoundException;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class MainController {

    private ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minCost, maxCost, titlePart, page).map(
                p -> new ProductDto(p)
        );
    }


    @GetMapping("/{id}")
    public Product getProductById (@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(" Product not found id" + id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/change_cost")
    public void changeCost(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeCost(productId, delta);
    }

    @PostMapping
    public ProductDto addProducts(@RequestBody ProductDto productDto) {
        productService.save(new Product(productDto.getId(), productDto.getTitle(), productDto.getCost()));
        return productDto;
    }

    @PutMapping
    public ProductDto updateProducts(@RequestBody ProductDto productDto) {
    Product product = productService.findById(productDto.getId()).get();
    product.setTitle(productDto.getTitle());
    product.setCost(productDto.getCost());
    return productDto;
    }


   /* @GetMapping("/cost_between")
    public List<Product> findStudentsByScoreBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "1000") Integer max) {
        return productService.findByScoreBetween(min, max);
    }  */


}
