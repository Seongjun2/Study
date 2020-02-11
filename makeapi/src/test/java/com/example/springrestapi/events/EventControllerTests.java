package com.example.springrestapi.events;

import com.example.springrestapi.common.TestDescription;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc;
    //MockMvc 를 사용하면 웹 컨테이너를 구동하지 않고 테스트 할 수 있다.
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception{
        Event event = Event.builder()
                .name("Spring")
                .description("REST API Development")
                .beginEnrollmentDateTime(LocalDateTime.of(2010,11,23,14,23))
                .closeEnrollmentDateTime(LocalDateTime.of(2018,11,30,14,23))
                .beginEventDateTime(LocalDateTime.of(2018,12,5,14,30))
                .endEventDateTime(LocalDateTime.of(2018,12,6,14,30))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("D start up Factory")
                .build();

        //생성한 event 객체를 api/events 경로로 post 메서드를 통해 이벤트 데이터를 생성하는 로직을 검사하는 테스트.
        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON_UTF8)
                .content(objectMapper.writeValueAsString(event))) // json으로 직렬화하여 http 메세지에 추가
                .andDo(print())// 테스트 콘솔창에 req, res 를 볼 수 있음
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE));
        //andExpect 메서드를 통해 어떤 응답이 올지 체크하는 로직을 쉽게 작성할 수 있음
        //HttpHeader 에 등록된 상수들을 통해 type-safe 한 코드를 작성할 수 있
    }

    @Test
    public void createEvent_Bad_Request() throws Exception{
        Event event = Event.builder()
                .name("Spring")
                .description("REST API Development")
                .beginEnrollmentDateTime(LocalDateTime.of(2010,11,23,14,23))
                .closeEnrollmentDateTime(LocalDateTime.of(2018,11,30,14,23))
                .beginEventDateTime(LocalDateTime.of(2018,12,5,14,30))
                .endEventDateTime(LocalDateTime.of(2018,12,6,14,30))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("D start up Factory")
                .free(true)
                .offline(false)
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON_UTF8)
                .content(objectMapper.writeValueAsString(event))) // json으로 직렬화하여 http 메세지에 추가
                .andDo(print())// 테스트 콘솔창에 req, res 를 볼 수 있음
                .andExpect(status().isBadRequest());
    }

    @Test
    @TestDescription("입력값이 비었을 때")
    public void createEvent_Bad_Request_Empty_Input() throws Exception{
        EventDto eventDto = EventDto.builder().build();

        this.mockMvc.perform(post("/api/events")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @TestDescription("잘못된 입력값이 입력되었을 때")
    public void createEvent_Bad_Request_Wrong_Input() throws Exception{
        EventDto eventDto = EventDto.builder()
                .name("Spring")
                .description("REST API Development")
                .beginEnrollmentDateTime(LocalDateTime.of(2010,11,23,14,23))
                .closeEnrollmentDateTime(LocalDateTime.of(2018,11,21,14,23))
                .beginEventDateTime(LocalDateTime.of(2018,12,24,14,30))
                .endEventDateTime(LocalDateTime.of(2018,12,6,14,30))
                .basePrice(10000)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("D start up Factory")
                .build();

        this.mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(this.objectMapper.writeValueAsString(eventDto)))
            .andExpect(status().isBadRequest());
    }
}
