package com.geekbrains.spring.web.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppError {
    private String code;
    private String message;


    public AppError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
