package com.practice.fc_springboot_covidproject.service;

import com.practice.fc_springboot_covidproject.domain.Admin;
import com.practice.fc_springboot_covidproject.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        return User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword())
                .authorities(List.of())
                .build();
    }
}


/*
 * security가 username을 받으면
 * DB에 접근해서 (22줄) 확인을 한다.
 * return 매칭되는 유저 인스턴스
 *
 *
 */