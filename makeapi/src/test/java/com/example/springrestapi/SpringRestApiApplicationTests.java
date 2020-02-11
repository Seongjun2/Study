package com.example.springrestapi;

import com.example.springrestapi.events.Event;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SpringRestApiApplicationTests {

    // 이벤트 도메인 객체가 builder 메서드를 가지고 있는 지 확인하는 테스트.
    @Test
    public void builder(){
        Event event = Event.builder().name("Spring REST API").description("REST API development").build();
        assertThat(event).isNotNull();
    }

    //javaBean은 도메인 객체가 자바빈을 준수하였는지 확인하는 테스트이다.
    @Test
    public void javaBean(){

        //given
        String name = "Event";
        String description = "Spring REST API";

        //when
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        //then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }
}
