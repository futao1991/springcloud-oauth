package com.tao.spring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class WebController {

    @PreAuthorize("hasAuthority('orderInfo')")
    @RequestMapping("/info")
    public String info() {
        return "order-service";
    }
}
