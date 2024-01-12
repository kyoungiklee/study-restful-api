package org.opennuri.study.restful.events.adapter.in.web;

import lombok.*;
import org.opennuri.study.restful.events.domain.EventStatus;

import java.time.LocalDateTime;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class EventResponse {

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
}
