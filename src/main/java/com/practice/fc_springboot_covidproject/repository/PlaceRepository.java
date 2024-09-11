package com.practice.fc_springboot_covidproject.repository;

import com.practice.fc_springboot_covidproject.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
