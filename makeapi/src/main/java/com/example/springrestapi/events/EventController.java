package com.example.springrestapi.events;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
// url 과 매핑된 요청을 처리 함, json 형태로서 값을 반환
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @PostMapping//post 요청으로 처리
    public ResponseEntity createEvent(@RequestBody Event event){//body 에 있는 정보가 자동적으로 Event 객체로 역직렬화되어 매핑된다.
        Event newEvent = this.eventRepository.save(event);
        URI createURI = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return ResponseEntity.created(createURI).body(newEvent);
    }
}
