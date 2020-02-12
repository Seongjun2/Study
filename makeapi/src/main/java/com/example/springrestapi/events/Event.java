package com.example.springrestapi.events;

import lombok.*;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity // 클래스가 엔티티
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")//entity 간에 상호 참조는 stack overflow 발생 가능. id로 비교하는 것이 바람직.
// @Data 쓰지 않는 이유도 @EqualsAndHashCode 재정의 하려고
public class Event {

    @Id @GeneratedValue //이 두개로 자동적으로 ID가 생성될 수 있게 한다.
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
    @Enumerated(EnumType.STRING)//enum 값이 문자열로 나타나도록 한다. default 값인 ORDINAL 로 하면 EnumType 의 순서가 바뀔시 에러가 날 수 있음
    private EventStatus eventStatus = EventStatus.DRAFT;


    public void update(){
        //update free
        if (this.basePrice == 0 && this.maxPrice == 0) {
            this.free = true;
        }
        else{
            this.free = false;
        }

        //update offline
        if(this.location == null || this.location.isEmpty()){
            this.offline = false;
        }
        else{
            this.offline = true;
        }
    }

}
