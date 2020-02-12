package com.example.springrestapi.events;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
// url 과 매핑된 요청을 처리 함, json 형태로서 값을 반환
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EventValidator eventValidator;

//    @PostMapping//post 요청으로 처리
//    public ResponseEntity createEvent(@RequestBody Event event){//body 에 있는 정보가 자동적으로 Event 객체로 역직렬화되어 매핑된다.
//        Event newEvent = this.eventRepository.save(event);
//        URI createURI = linkTo(EventController.class).slash(newEvent.getId()).toUri();
//        return ResponseEntity.created(createURI).body(newEvent);
//    }

    /*
    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDto eventDto){
        Event event = modelMapper.map(eventDto, Event.class);
        Event newEvent = this.eventRepository.save(event);
        URI createURI = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return ResponseEntity.created(createURI).body(newEvent);
    }
    */

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        eventValidator.validate(eventDto, errors);

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        Event newEvent = this.eventRepository.save(event);

        //HATEOAS link added
        ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
        URI createdURI = selfLinkBuilder.toUri();
        EventResource eventResource = new EventResource(newEvent);
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        eventResource.add(linkTo(EventController.class).withRel("update-events"));
        return ResponseEntity.created(createdURI).body(eventResource);
        /*
        컨트롤러에서의 메서드에 위와 같이 @Valid 어노테이션을 붙이면 스프링에서 자동적으로 EventDto 에 들어갈 값들을 체크하여 만약 에러가 발생했을 경우 Errors 객체에 집어넣습니다.
        @Valid 어노테이션은 위에 EventDto 클래스의 프로퍼티에 붙인 @NotNull, @NotEmpty 등의 어노테이션 정보를 토대로 해당 객체에 대한 유효성 여부를 검사합니다.
        eventValidator 는 validate 메서드를 통해서 객체의 유효성 여부를 검사하며 만약 에러가 발생할 경우 Errors 객체에 집어넣습니다.
        errors 에 에러에 대한 정보가 있는 지 여부는 hasErrors 메서드를 통해 판별할 수 있습니다. 만약 에러가 있을 경우 badRequest 를 반환하도록 로직을 구성했습니다.
         */


    }
}
