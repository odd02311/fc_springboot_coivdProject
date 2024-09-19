package com.practice.fc_springboot_covidproject.dto;

import com.practice.fc_springboot_covidproject.constant.EventStatus;

import java.time.LocalDateTime;

public record EventViewResponse(
    Long id,
    String placeName,
    String eventName,
    EventStatus eventStatus,
    LocalDateTime eventStartDatetime,
    LocalDateTime eventEndDatetime,
    Integer currentNumberOfPeople,
    Integer capacity,
    String memo
) {
  public EventViewResponse(Long id, String placeName, String eventName, EventStatus eventStatus, LocalDateTime eventStartDatetime, LocalDateTime eventEndDatetime, Integer currentNumberOfPeople, Integer capacity, String memo) {
    this.id = id;
    this.placeName = placeName;
    this.eventName = eventName;
    this.eventStatus = eventStatus;
    this.eventStartDatetime = eventStartDatetime;
    this.eventEndDatetime = eventEndDatetime;
    this.currentNumberOfPeople = currentNumberOfPeople;
    this.capacity = capacity;
    this.memo = memo;
  }

  public static EventViewResponse of(
      Long id,
      String placeName,
      String eventName,
      EventStatus eventStatus,
      LocalDateTime eventStartDatetime,
      LocalDateTime eventEndDatetime,
      Integer currentNumberOfPeople,
      Integer capacity,
      String memo
  ) {
    return new EventViewResponse(
        id,
        placeName,
        eventName,
        eventStatus,
        eventStartDatetime,
        eventEndDatetime,
        currentNumberOfPeople,
        capacity,
        memo
    );
  }

  public static EventViewResponse from(EventDto eventDto) {
    if (eventDto == null) { return null; }
    return EventViewResponse.of(
            eventDto.id(),
            eventDto.placeDto().placeName(),
            eventDto.eventName(),
            eventDto.eventStatus(),
            eventDto.eventStartDatetime(),
            eventDto.eventEndDatetime(),
            eventDto.currentNumberOfPeople(),
            eventDto.capacity(),
            eventDto.memo()
    );
  }
}

/*
  view 응답으로만 사용할 Dto


  기존 eventResponse가 DTO를 직접 썼다.
  event안에 place가 들어있는, 즉 entity 안에 entity가 들어있는 구조이다.
  하지만 view response는 무겁게 받을 필요없이 그냥 place name만 받기로 한다.
 */