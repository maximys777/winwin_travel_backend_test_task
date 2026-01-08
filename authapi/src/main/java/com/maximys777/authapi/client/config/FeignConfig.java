package com.maximys777.authapi.client.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            HttpStatus status = HttpStatus.valueOf(response.status());

            return switch (status) {
                case BAD_REQUEST -> new IllegalArgumentException("Invalid request for Service B");
                case UNAUTHORIZED, FORBIDDEN ->
                        new SecurityException("Authorization error between services (Invalid X-Internal-Token)");
                case NOT_FOUND -> new RuntimeException("Resource not found in Service B");
                case INTERNAL_SERVER_ERROR -> new RuntimeException("Service B crashed");
                default -> new RuntimeException("An unexpected error occurred while calling Service B: " + status);
            };
        };
    }
}
