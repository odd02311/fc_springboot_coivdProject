package com.practice.fc_springboot_covidproject.repository;

import com.practice.fc_springboot_covidproject.domain.Event;
import com.practice.fc_springboot_covidproject.domain.QEvent;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

//public interface EventRepository extends JpaRepository<Event, Long> {
//    // TODO: CREATE 리스트, 단일 조회, 생성, 수정, 삭제
//    // TODO: 인스턴스 생성 편의를 위해 임시로 default 사용, repository layer 구현이 완성되면 삭제
//
//    default List<EventDTO> findEvents(
//            Long placeId,
//            String eventName,
//            EventStatus eventStatus,
//            LocalDateTime eventStartDatetime,
//            LocalDateTime eventEndDatetime
//    ) { return List.of(); }
//    default Optional<EventDTO> findEvent(Long eventId) { return Optional.empty(); }
//    default boolean insertEvent(EventDTO eventDTO) { return false; }
//    default boolean updateEvent(Long eventId, EventDTO dto) { return false; }
//    default boolean deleteEvent(Long eventId) { return false; }
//}

public interface EventRepository extends
        JpaRepository<Event, Long>,
        QuerydslPredicateExecutor<Event>,
        QuerydslBinderCustomizer<QEvent> {

//    Optional<Event> findByPlaceIdAndEventNameAndAndCapacity(); // 세세한 컨트롤의 쿼리 메소드

    @Override
    default void customize(QuerydslBindings bindings, QEvent root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.placeId, root.eventName, root.eventStatus, root.eventStartDatetime, root.eventEndDatetime);
        bindings.bind(root.eventName).first(StringExpression::contains);
        bindings.bind(root.eventStartDatetime).first(ComparableExpression::goe);
        bindings.bind(root.eventEndDatetime).first(ComparableExpression::loe);
    }

}