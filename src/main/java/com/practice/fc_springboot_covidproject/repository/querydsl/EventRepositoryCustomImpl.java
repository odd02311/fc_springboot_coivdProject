package com.practice.fc_springboot_covidproject.repository.querydsl;

import com.practice.fc_springboot_covidproject.constant.ErrorCode;
import com.practice.fc_springboot_covidproject.constant.EventStatus;
import com.practice.fc_springboot_covidproject.domain.Event;
import com.practice.fc_springboot_covidproject.domain.QEvent;
import com.practice.fc_springboot_covidproject.dto.EventViewResponse;
import com.practice.fc_springboot_covidproject.exception.GeneralException;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventRepositoryCustomImpl extends QuerydslRepositorySupport implements EventRepositoryCustom{

  public EventRepositoryCustomImpl(){
    super(Event.class);
  }



  @Override
  public Page<EventViewResponse> findEventViewPageBySearchParams(  // paging 기능
      String placeName,
      String eventName,
      EventStatus eventStatus,
      LocalDateTime eventStartDatetime,
      LocalDateTime eventEndDatetime,
      Pageable pageable
  ) {
    // Qclass 사용
    QEvent event = QEvent.event;


    JPQLQuery<EventViewResponse> query = from(event)
        .select(Projections.constructor(
            EventViewResponse.class,
            event.id,
            event.place.placeName,
            event.eventName,
            event.eventStatus,
            event.eventStartDatetime,
            event.eventEndDatetime,
            event.currentNumberOfPeople,
            event.capacity,
            event.memo
        ));

    if(placeName != null && !placeName.isBlank()){
      query.where(event.place.placeName.containsIgnoreCase(placeName));
    }

    if(eventName != null && !eventName.isBlank()){
      query.where(event.eventName.containsIgnoreCase(eventName));
    }

    if(eventStatus != null){
      query.where(event.eventStatus.eq(eventStatus));
    }

    if(eventStartDatetime != null){
      query.where(event.eventStartDatetime.goe(eventStartDatetime)); // goe == greater or equal
    }

    if(eventEndDatetime != null){
      query.where(event.eventEndDatetime.loe(eventEndDatetime));
    }

    // query가 where절까지 완성되었으니 나머지 부분 조립
    List<EventViewResponse> events = Optional.ofNullable(getQuerydsl())
        .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR, "Spring Data JPA 로부터 Querydsl 인스턴스를 못 가져옴"))
        .applyPagination(pageable, query)
        .fetch();

    return new PageImpl<>(events, pageable, query.fetchCount()); // 페이징 방식 설정
  }
}

/**
 * QuerydslRepositorySupport를 사용하기 위해서 생성자를 만들어줘야 한다.
 * EventRepository에 해당하는 domain entity를 넣어주면 된다.
 * from으로 시작하고 from(Qclass)를 넣어주면 된다.
 *
 * select를 할때 그냥 이벤트를 가져올때는 select(event)를 했으면 됐지만
 * 지금은 이벤트 + 장소에 있는 이름을 가져올 것이다.
 * 그렇기 때문에 직접 커스텀 프로젝션을 해야 한다.
 * 크게 3가지 방법이 있는데
 * 1. setter 주입
 * 2. 생성자 주입
 * 3. query projection 어노테이션 활용
 *
 * apllyPagination(pageable정보, 쿼리정보)
 * .fetch로 데이터를 가져오면 된다.
 *
 * 페이징 방식 설정: PageImple<> (List, call 페이징 정보 페이지업, 카운트)
 * 이때 카운트는 events의 카운트가 아니다 토탈 카운트이다. 그러므로 events.size를 넣으면 안된다.
 * 페이징 정보를 넣어주지않은 카운터 쿼리가 되야한다.
 */