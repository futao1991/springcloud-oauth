package com.tao.spring.oauthserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }

}
