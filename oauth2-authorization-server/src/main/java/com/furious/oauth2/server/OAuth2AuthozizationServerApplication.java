package com.furious.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class OAuth2AuthozizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthozizationServerApplication.class, args);
    }
}
