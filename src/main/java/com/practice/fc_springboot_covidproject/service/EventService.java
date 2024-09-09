package com.practice.fc_springboot_covidproject.service;

import com.practice.fc_springboot_covidproject.constant.ErrorCode;
import com.practice.fc_springboot_covidproject.constant.EventStatus;
import com.practice.fc_springboot_covidproject.dto.EventDTO;
import com.practice.fc_springboot_covidproject.exception.GeneralException;
import com.practice.fc_springboot_covidproject.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDatetime
    ) {
        try {
            return eventRepository.findEvents(
                    placeId,
                    eventName,
                    eventStatus,
                    eventStartDateTime,
                    eventEndDatetime
            );
        } catch(Exception e){
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }

    }

    public Optional<EventDTO> getEvent(Long eventId) {
        return eventRepository.findEvent(eventId);
    }

    public boolean createEvent(EventDTO eventDTO) {
        return eventRepository.insertEvent(eventDTO);
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO) {
        return eventRepository.updateEvent(eventId, eventDTO);
    }

    public boolean removeEvent(Long eventId) {
        return eventRepository.deleteEvent(eventId);
    }

}
