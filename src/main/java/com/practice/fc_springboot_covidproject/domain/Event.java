package com.practice.fc_springboot_covidproject.domain;

import com.practice.fc_springboot_covidproject.constant.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "placeId"),
        @Index(columnList = "eventName"),
        @Index(columnList = "eventStartDatetime"),
        @Index(columnList = "eventEndDatetime"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "modifiedAt")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Event {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @ManyToOne(optional = false)
    private Place place;    // 장소 하나가 여러 개의 이벤트를 가지고 있을 수 있다.
    // ex: 배드민턴장 하나는 오전운동, 오후운동 이벤트를 가지고 있을 수 있다. 그렇기 때문에 @ManyToOne


    @Setter
    @Column(nullable = false)
    private String eventName;

    @Setter
    @Column(nullable = false, columnDefinition = "varchar(20) default 'OPENED'")
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @Setter
    @Column(nullable = false, columnDefinition = "datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventStartDatetime;

    @Setter @Column(nullable = false, columnDefinition = "datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventEndDatetime;

    @Setter @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer currentNumberOfPeople;

    @Setter @Column(nullable = false)
    private Integer capacity;

    @Setter
    private String memo;


    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedAt;


    protected Event() {}

    protected Event(
            Place place,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        this.place = place;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
        this.currentNumberOfPeople = currentNumberOfPeople;
        this.capacity = capacity;
        this.memo = memo;
    }

    public static Event of(
            Place place,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        return new Event(
                place,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                currentNumberOfPeople,
                capacity,
                memo
        );
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return id != null && id.equals(((Event) obj).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, eventStartDatetime, eventEndDatetime, createdAt, modifiedAt);
    }


}

/**
 * @Entity 는 데이터베이스 테이블과 연동이 되는
 * 서로 같이 연관관계를 지을수 있는 도메인 자바 오브젝트를 만든다.
 * 실제로 데이터베이스와 연동이 된다는걸 JPA한테 알려주는 역할
 *
 *
 * @ManyToOne private Place place; 에서의 fetch 전략
 * 우선 event(entity)는 place를 알고 있나? 알고있다. event안을 보면 이벤트는 장소의 존재를 알고 있다.
 * place의 존재를 실제로 객체에서 맵핑을 통해 알고 있다. 데이터베이스 테이블에선 id 형태로 들고있다.
 * 알고있다. 즉 그 해당 데이터를 언제든지 접글할 수 있는 기능과 니즈가 있음을 의미한다.
 * 그렇기때문에 @ManyToOne 문서를 보면 기본 전략이 FetchType fetch() default EAGER;로 되어있음을 알 수 있다.
 * 왜냐하면 이벤트를 불러오더라도 언제든지 place 안에 있는 장소 이름이나 주소나 이런 것들에 접근하고 싶을
 * 니즈가 예측이 된다.
 *
 * 그럼 반면에 장소쪽에서 이벤트는 어떨까?
 * 장소는 이벤트의 존재를 알고 있나? 그렇진 않다. place table의 스키마는 이벤트에 대한 정보가 없다.
 * 기본적으로 장소는 이벤트 정보를 가지고 있지 않다. 또 가지고 있을 필요도 없다.
 * 장소는 이벤트가 없는 장소도 있을 수 있는 것이다.
 * 있을 수도 있고 없을 수도 있는 이벤트 정보를 장소가 반드시 알아야 되는 그런 상황은 아닌거다.
 * 즉, 데이터에 접근할 가능성이 상대적으로 낮은 거고
 * 그렇기 때문에 @OneToMany의 기본 fetch 전략은 lazy가 되는 것이다.
 *
 */