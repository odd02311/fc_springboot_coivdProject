package com.practice.fc_springboot_covidproject.config;

import com.practice.fc_springboot_covidproject.repository.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public EventRepository eventRepository() {
        return  new EventRepository() {};
    }
}
