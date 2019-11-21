package com.simple_springboot_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/simple")
public class SimpleController {

    @GetMapping("/hello")
    public String hello() {
       return "Hello from simple controller";
    }
}
