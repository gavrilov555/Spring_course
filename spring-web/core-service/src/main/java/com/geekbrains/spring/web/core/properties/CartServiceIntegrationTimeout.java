package com.geekbrains.spring.web.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.core-service.timeouts")
@Data
public class CartServiceIntegrationTimeout {
    private Integer connect;
    private Integer read;
    private Integer write;

}
