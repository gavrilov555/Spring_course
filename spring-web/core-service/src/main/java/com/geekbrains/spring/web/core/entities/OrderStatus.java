package com.geekbrains.spring.web.core.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    CREATED(1, "Создан"),
    PAIDED(2, "Оплачен"),
    CANCELED(3, "Отменен");

    private final int id;

    private final String description;
}
