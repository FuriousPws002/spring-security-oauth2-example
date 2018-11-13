package com.furious.oauth2.server.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public Object user() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
