package com.practice.fc_springboot_covidproject.repository;

import com.practice.fc_springboot_covidproject.constant.EventStatus;
import com.practice.fc_springboot_covidproject.constant.PlaceType;
import com.practice.fc_springboot_covidproject.domain.Event;
import com.practice.fc_springboot_covidproject.domain.Place;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EventRepositoryTest {

    private final EventRepository sut;
    private final TestEntityManager testEntityManager;


    EventRepositoryTest(
            @Autowired EventRepository sut,
            @Autowired TestEntityManager testEntityManager
    ) {
        this.sut = sut;
        this.testEntityManager = testEntityManager;
    }

    @DisplayName("test")
    @Test
    void test(){
        // Given
        Place place = createPlace();
        Event event = createEvent(place);
        testEntityManager.persist(place);
        testEntityManager.persist(event);


        // When
       Iterable<Event> events = sut.findAll(new BooleanBuilder());

        // Then
        assertThat(events).hasSize(7);


    }

    private Event createEvent(Place place){
        return createEvent(
                place,
                "test event",
                EventStatus.ABORTED,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private Event createEvent(
            Place place,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ) {
        return Event.of(
                place,
                eventName,
                eventStatus,
                eventStartDateTime,
                eventEndDateTime,
                0,
                24,
                "마스크 꼭 착용하세요"
        );

    }


    private Place createPlace() {
        return Place.of(PlaceType.COMMON, "test place", "test address", "010-1234-1234", 10, null);

    }

}