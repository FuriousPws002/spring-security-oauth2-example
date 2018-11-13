package com.furious.oauth2.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class OAuth2ResouceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ResouceServerApplication.class, args);
    }
}
