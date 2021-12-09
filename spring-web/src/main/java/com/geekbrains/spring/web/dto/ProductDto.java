package com.geekbrains.spring.web.dto;


import com.geekbrains.spring.web.data.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private Integer cost;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        }
}
