package com.practice.fc_springboot_covidproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BaseController implements ErrorController {

    @GetMapping("/")
    public String root() {

        return "index";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }

//    @GetMapping("/api/hello")
//    public String test(){
//        return "Hello, world!";
//    } 리액트 테스트용도



}
