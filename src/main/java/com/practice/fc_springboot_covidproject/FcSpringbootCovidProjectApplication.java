package com.practice.fc_springboot_covidproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FcSpringbootCovidProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcSpringbootCovidProjectApplication.class, args);
    }

}
