package org.opennuri.study.restful.events.adapter.in.web.index;


import org.opennuri.study.restful.events.adapter.in.web.event.EventController;
import org.opennuri.study.restful.events.adapter.in.web.index.dto.Index;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class IndexController {

    @GetMapping("/api/")
    public ResponseEntity<RepresentationModel<Index>> index() {
        var index = new RepresentationModel<Index>();
        index.add(linkTo(EventController.class).withRel("events"));
        return ResponseEntity.ok(index);
    }
}
