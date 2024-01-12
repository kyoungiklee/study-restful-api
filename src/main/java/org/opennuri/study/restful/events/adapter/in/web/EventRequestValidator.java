package org.opennuri.study.restful.events.adapter.in.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class EventRequestValidator {
    public void validate(EventRequest eventRequest, Errors errors) {
        if (eventRequest.getBasePrice() > eventRequest.getMaxPrice() && eventRequest.getMaxPrice() != 0) {
            errors.rejectValue("basePrice", "wrongValue", "BasePrice is wrong.");
            errors.rejectValue("maxPrice", "wrongValue", "MaxPrice is wrong.");
        }

        if (eventRequest.getEndEventDateTime().isBefore(eventRequest.getBeginEventDateTime()) ||
                eventRequest.getEndEventDateTime().isBefore(eventRequest.getCloseEnrollmentDateTime()) ||
                eventRequest.getEndEventDateTime().isBefore(eventRequest.getBeginEnrollmentDateTime())) {
            errors.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is wrong.");
        }

        if (eventRequest.getBeginEventDateTime().isBefore(eventRequest.getBeginEnrollmentDateTime()) ||
                eventRequest.getBeginEventDateTime().isBefore(eventRequest.getCloseEnrollmentDateTime())) {
            errors.rejectValue("beginEventDateTime", "wrongValue", "BeginEventDateTime is wrong.");
        }

        if (eventRequest.getCloseEnrollmentDateTime().isBefore(eventRequest.getBeginEnrollmentDateTime())) {
            errors.rejectValue("closeEnrollmentDateTime", "wrongValue", "CloseEnrollmentDateTime is wrong.");
        }

        if (eventRequest.getLimitOfEnrollment() < 0) {
            errors.rejectValue("limitOfEnrollment", "wrongValue", "LimitOfEnrollment is wrong.");
        }

    }
}
