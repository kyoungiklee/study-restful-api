package org.opennuri.study.restful.events.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.opennuri.study.restful.common.annotation.WebAdapter;
import org.opennuri.study.restful.events.application.port.in.EventCommand;
import org.opennuri.study.restful.events.application.port.in.EventUseCase;
import org.opennuri.study.restful.events.domain.Event;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@WebAdapter
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    private final EventUseCase eventUseCase;
    private final ModelMapper modelMapper;
    private final EventRequestValidator eventRequestValidator;
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody @Valid EventRequest request, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        eventRequestValidator.validate(request, errors);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        EventCommand command = modelMapper.map(request, EventCommand.class);

        /*EventCommand command = EventCommand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .beginEnrollmentDateTime(request.getBeginEnrollmentDateTime())
                .closeEnrollmentDateTime(request.getCloseEnrollmentDateTime())
                .beginEventDateTime(request.getBeginEventDateTime())
                .endEventDateTime(request.getEndEventDateTime())
                .location(request.getLocation())
                .basePrice(request.getBasePrice())
                .maxPrice(request.getMaxPrice())
                .limitOfEnrollment(request.getLimitOfEnrollment())
                .build();*/

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
        //Header에 Location 정보를 담아서 보내준다.
        return ResponseEntity.created(uri).body(response);
    }
}
