package com.example.springrestapi.events;

import lombok.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")//entity 간에 상호 참조는 stack overflow 발생 가능. id로 비교하는 것이 바람직.
// @Data 쓰지 않는 이유도 @EqualsAndHashCode 재정의 하려고
public class Event {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    private EventStatus eventStatus = EventStatus.DRAFT;


}
