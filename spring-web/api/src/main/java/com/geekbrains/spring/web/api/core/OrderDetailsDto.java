package com.geekbrains.spring.web.api.core;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {

    private String username;
    private String name;
    private String surname;
    private String street;
    private String house;
    private String flat;
    private String city;
    private String district;
    private String postalCode;
    private String countryCode;
    private String phone;

}
