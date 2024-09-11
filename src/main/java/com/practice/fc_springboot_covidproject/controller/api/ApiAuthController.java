package com.practice.fc_springboot_covidproject.controller.api;

import com.practice.fc_springboot_covidproject.dto.APIDataResponse;
import com.practice.fc_springboot_covidproject.dto.AdminRequest;
import com.practice.fc_springboot_covidproject.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/api")
//@RestController
@Deprecated
public class ApiAuthController {

    @PostMapping("/sign-up")
    public APIDataResponse<String> signUp(@RequestBody AdminRequest adminRequest) {
        return APIDataResponse.empty();
    }

    @PostMapping("/login")
    public APIDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return APIDataResponse.empty();
    }


}
