package org.opennuri.study.restful.events.adapter.in.web.event.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import org.opennuri.study.restful.events.adapter.in.web.event.EventController;
import org.opennuri.study.restful.events.adapter.in.web.event.dto.EventResponse;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Getter
public class EventResource extends EntityModel<EventResource> {

    @JsonUnwrapped
    private EventResponse response;

    public EventResource(EventResponse response) {
        this.response = response;
        add(linkTo(EventController.class).slash(response.getId()).withSelfRel());
    }

}
