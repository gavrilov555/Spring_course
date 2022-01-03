package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.data.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;
    private int costPerProduct;
    private int cost;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.costPerProduct = product.getCost();
        this.cost = product.getCost();
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.cost = this.quantity * this.costPerProduct;
    }
}
