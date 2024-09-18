package com.practice.fc_springboot_covidproject.repository.querydsl;

import com.practice.fc_springboot_covidproject.constant.EventStatus;
import com.practice.fc_springboot_covidproject.dto.EventViewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface EventRepositoryCustom {
  
  // Paging 기능
  Page<EventViewResponse> findEventViewPageBySearchParams(
      String placeName,
      String eventName,
      EventStatus eventStatus,
      LocalDateTime eventStartDatetime,
      LocalDateTime eventEndDatetime,
      Pageable pageable
  );
  
}


/**
 * EventRepository는 JPA이기 때문에 따로 구현체를 설정해 주지않았지만
 * QeuryDsl은 구현체를 꼭 설정 해주어야만 한다.
 * 그리고 구현체는 impl을 꼭 달아줘야만 한다
 * 설정을 통해 바꿀수도 있지만 기본 규칙은 Impl
 */