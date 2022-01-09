package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.data.Order;
import com.geekbrains.spring.web.data.OrderItem;
import com.geekbrains.spring.web.dto.Cart;
import com.geekbrains.spring.web.dto.OrderItemDto;
import com.geekbrains.spring.web.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;
    private final OrderItemService orderItemService;


    public void createOrder(String address, String phone, String username) {
        Long userId = userService.findByUsername(username).get().getId();
        Cart currentCart = cartService.getCurrentCart();

        long orderId = orderRepository.save(new Order(
                null,
                userId,
                currentCart.getTotalCost(),
                address,
                phone
        )).getId();

        List<OrderItemDto> list = currentCart.getItems();

        for (OrderItemDto orderItem : list) {
            orderItemService.save(new OrderItem(
                    null,
                    orderItem.getProductId(),
                    userId,
                    orderId,
                    orderItem.getQuantity(),
                    orderItem.getCostPerProduct(),
                    orderItem.getCost()
            ));

        }
    }
}