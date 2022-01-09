package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @GetMapping()
    public void createOrder(@RequestParam(name = "address") String address,
                            @RequestParam(name = "phone") String phone,
                            @RequestParam(name = "user_name") String userName) {
        orderService.createOrder(address, phone, userName);
    }
}
