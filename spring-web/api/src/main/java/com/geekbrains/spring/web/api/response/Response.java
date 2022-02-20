package com.geekbrains.spring.web.api.response;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {
    public Response(int code, boolean success, String message, ProductDto productDto) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.productDto = productDto;
    }

    int code;
    boolean success;
    String message;
    ProductDto productDto;
}
