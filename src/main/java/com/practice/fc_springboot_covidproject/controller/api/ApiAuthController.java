package com.practice.fc_springboot_covidproject.controller.api;

import com.practice.fc_springboot_covidproject.dto.ApiDataResponse;
import com.practice.fc_springboot_covidproject.dto.AdminRequest;
import com.practice.fc_springboot_covidproject.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/api")
//@RestController
@Deprecated
public class ApiAuthController {

    @PostMapping("/sign-up")
    public ApiDataResponse<String> signUp(@RequestBody AdminRequest adminRequest) {
        return ApiDataResponse.empty();
    }

    @PostMapping("/login")
    public ApiDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return ApiDataResponse.empty();
    }


}
