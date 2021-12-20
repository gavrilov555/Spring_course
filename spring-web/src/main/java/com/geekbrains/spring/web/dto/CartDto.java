package com.geekbrains.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;
    private String title;
    private Integer count;
    private Integer cost;


}
