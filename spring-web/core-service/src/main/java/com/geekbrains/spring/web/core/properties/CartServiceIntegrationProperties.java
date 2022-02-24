package com.geekbrains.spring.web.core.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.core-service")
@Data
public class CartServiceIntegrationProperties {

    private String url;
    private CartServiceIntegrationTimeout timeout;

}