package com.practice.fc_springboot_covidproject.repository;

import com.practice.fc_springboot_covidproject.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String email);
}
