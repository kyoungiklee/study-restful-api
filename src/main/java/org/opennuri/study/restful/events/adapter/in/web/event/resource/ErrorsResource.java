package org.opennuri.study.restful.events.adapter.in.web.event.resource;

import org.opennuri.study.restful.events.adapter.in.web.index.IndexController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.Errors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ErrorsResource extends RepresentationModel<ErrorsResource> {

        private Errors errors;

        public ErrorsResource(Errors errors) {
            this.errors = errors;
            add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
        }

        public Errors getErrors() {
            return errors;
        }
}
