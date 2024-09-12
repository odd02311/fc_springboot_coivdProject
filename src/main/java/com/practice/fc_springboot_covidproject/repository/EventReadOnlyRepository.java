package com.practice.fc_springboot_covidproject.repository;

import com.practice.fc_springboot_covidproject.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface EventReadOnlyRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(Long id);
    List<T> findAll();
    List<T> findAllById(Iterable<Long> ids);
    List<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);

}
