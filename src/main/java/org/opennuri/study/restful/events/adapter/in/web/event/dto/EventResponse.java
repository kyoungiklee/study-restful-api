package org.opennuri.study.restful.events.adapter.in.web.event.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.opennuri.study.restful.events.adapter.in.web.event.resource.ErrorsResource;
import org.opennuri.study.restful.events.domain.EventStatus;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class EventResponse extends EntityModel<EventResponse> {

    private  Long id;
    private  String name;
    private  String description;
    private  LocalDateTime beginEnrollmentDateTime;
    private  LocalDateTime closeEnrollmentDateTime;
    private  LocalDateTime beginEventDateTime;
    private  LocalDateTime endEventDateTime;
    private  String location;
    private  int basePrice;
    private  int maxPrice;
    private  int limitOfEnrollment;
    private  boolean offline;
    private  boolean free;
    private  EventStatus status;
    @JsonUnwrapped
    private ErrorsResource errors;
}
