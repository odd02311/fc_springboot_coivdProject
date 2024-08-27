package com.practice.fc_springboot_covidproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BaseController {

    @GetMapping("/")
    public String root() throws Exception{
        throw new Exception("테스트에러");
//        return "index";
    }

//    @RequestMapping("/error")
//    public String error() {
//        return "error";
//    }

//    @GetMapping("/api/hello")
//    public String test(){
//        return "Hello, world!";
//    } 리액트 테스트용도



}
