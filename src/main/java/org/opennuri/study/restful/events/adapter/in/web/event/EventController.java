package org.opennuri.study.restful.events.adapter.in.web.event;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.opennuri.study.restful.common.annotation.WebAdapter;
import org.opennuri.study.restful.events.adapter.in.web.event.dto.EventRequest;
import org.opennuri.study.restful.events.adapter.in.web.event.dto.EventResponse;
import org.opennuri.study.restful.events.adapter.in.web.event.resource.EventResource;
import org.opennuri.study.restful.events.adapter.in.web.event.validator.EventRequestValidator;
import org.opennuri.study.restful.events.application.port.in.EventCommand;
import org.opennuri.study.restful.events.application.port.in.EventUseCase;
import org.opennuri.study.restful.events.domain.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@WebAdapter
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    private final EventUseCase eventUseCase;
    private final ModelMapper modelMapper;
    private final EventRequestValidator eventRequestValidator;
    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventRequest request, Errors errors) {
        // DTO 필드 검증
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        // DTO 비즈니스 로직 검증
        eventRequestValidator.validate(request, errors);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        EventCommand command = modelMapper.map(request, EventCommand.class);

        command.update();

        Event event = eventUseCase.createEvent(command);

        EventResponse response = EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .beginEnrollmentDateTime(event.getBeginEnrollmentDateTime())
                .closeEnrollmentDateTime(event.getCloseEnrollmentDateTime())
                .beginEventDateTime(event.getBeginEventDateTime())
                .endEventDateTime(event.getEndEventDateTime())
                .location(event.getLocation())
                .basePrice(event.getBasePrice())
                .maxPrice(event.getMaxPrice())
                .limitOfEnrollment(event.getLimitOfEnrollment())
                .offline(event.isOffline())
                .free(event.isFree())
                .status(event.getStatus())
                .build();

        URI uri = linkTo(EventController.class).slash("{id}").withSelfRel().toUri();
        EntityModel<EventResponse> eventResource = EntityModel.of(response);

        // 자신의 uri를 가지고 있는 self link를 추가한다.
        eventResource.add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
        // query-events라는 이름으로 이벤트 목록을 조회할 수 있는 link를 추가한다.
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        // update-event라는 이름으로 이벤트를 수정할 수 있는 link를 추가한다.
        eventResource.add(linkTo(EventController.class).slash(event.getId()).withRel("update-event"));

        //Header에 Location 정보를 담아서 보내준다.
        return ResponseEntity.created(uri).body(eventResource);
    }
}
