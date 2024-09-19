package com.practice.fc_springboot_covidproject.controller;

import com.practice.fc_springboot_covidproject.constant.EventStatus;
import com.practice.fc_springboot_covidproject.service.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
class EventControllerTest {

    private final MockMvc mvc;

    @MockBean
    private EventService eventService;

    public EventControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
    
    @DisplayName("[view][GET] 이벤트 리스트 페이지")
    @Test
    void givenNothing_whenRequestEventsPage_thenReturnsEventsPage() throws Exception{
        // given

        // when & then
        mvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("event/index"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("events"));
    }

    @DisplayName("[view][GET] 이벤트 리스트 페이지 - 커스텀 데이터")
    @Test
    void givenNothing_whenRequestingCustomEventsPage_thenReturnsEventsPage() throws Exception {
        // Given
        given(eventService.getEventViewResponse(any(), any(), any(), any(), any(), any())).willReturn(Page.empty());

        // When & Then
        mvc.perform(get("/events/custom"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("event/index"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("events"));
        then(eventService).should().getEventViewResponse(any(), any(), any(), any(), any(), any());
    }

    @DisplayName("[view][GET] 이벤트 리스트 페이지 - 커스텀 데이터 + 검색 파라미터")
    @Test
    void givenParams_whenRequestingCustomEventsPage_thenReturnsEventsPage() throws Exception {
        // Given
        String placeName = "배드민턴";
        String eventName = "오후";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDatetime = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        LocalDateTime eventEndDatetime = LocalDateTime.of(2021, 1, 2, 0, 0, 0);
        given(eventService.getEventViewResponse(
                placeName,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                PageRequest.of(1, 3)
        )).willReturn(Page.empty());

        // When & Then
        mvc.perform(
                        get("/events/custom")
                                .queryParam("placeName", placeName)
                                .queryParam("eventName", eventName)
                                .queryParam("eventStatus", eventStatus.name())
                                .queryParam("eventStartDatetime", eventStartDatetime.toString())
                                .queryParam("eventEndDatetime", eventEndDatetime.toString())
                                .queryParam("page", "1")
                                .queryParam("size", "3")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("event/index"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("events"));
        then(eventService).should().getEventViewResponse(
                placeName,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                PageRequest.of(1, 3)
        );
    }

    @DisplayName("[view][GET] 이벤트 리스트 페이지 - 커스텀 데이터 + 검색 파라미터 (장소명, 이벤트명 잘못된 입력)")
    @Test
    void givenWrongParams_whenRequestingCustomEventsPage_thenReturnsEventsPage() throws Exception {
        // Given
        String placeName = "배";
        String eventName = "오";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDatetime = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        LocalDateTime eventEndDatetime = LocalDateTime.of(2021, 1, 2, 0, 0, 0);
        given(eventService.getEventViewResponse(
                placeName,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                PageRequest.of(1, 3)
        )).willReturn(Page.empty());

        // When & Then
        mvc.perform(
                        get("/events/custom")
                                .queryParam("placeName", placeName)
                                .queryParam("eventName", eventName)
                                .queryParam("eventStatus", eventStatus.name())
                                .queryParam("eventStartDatetime", eventStartDatetime.toString())
                                .queryParam("eventEndDatetime", eventEndDatetime.toString())
                                .queryParam("page", "1")
                                .queryParam("size", "3")
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("error"))
                .andExpect(model().attributeDoesNotExist("events"));
        then(eventService).shouldHaveNoInteractions();
    }
    
    @DisplayName("[view][GET] 이벤트 세부 정보 페이지")
    @Test
    void givenNothing_whenRequestEventDetailPage_thenReturnsEventDetailPage() throws Exception{
        // given
        long eventId = 1L;

        // when & then
        mvc.perform(get("/events/" + eventId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("event/detail"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("event"));

    }

}