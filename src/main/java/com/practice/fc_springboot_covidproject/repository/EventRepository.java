package com.practice.fc_springboot_covidproject.repository;

import com.practice.fc_springboot_covidproject.constant.EventStatus;
import com.practice.fc_springboot_covidproject.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository {
    // TODO: CREATE 리스트, 단일 조회, 생성, 수정, 삭제
    // TODO: 인스턴스 생성 편의를 위해 임시로 default 사용, repository layer 구현이 완성되면 삭제

    default List<EventDTO> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) { return List.of(); }
    default Optional<EventDTO> findEvent(Long eventId) { return Optional.empty(); }
    default boolean insertEvent(EventDTO eventDTO) { return false; }
    default boolean updateEvent(Long eventId, EventDTO dto) { return false; }
    default boolean deleteEvent(Long eventId) { return false; }
}
