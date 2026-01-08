package com.maximys777.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthapiApplication.class, args);
    }

}
