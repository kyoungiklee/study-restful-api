package org.opennuri.study.restful.events.application.port.out;

import org.opennuri.study.restful.events.domain.Event;

public interface EventPort {
    Event saveEvent(
            Event.Name name,
            Event.Description description,
            Event.BeginEnrollmentDateTime beginEnrollmentDateTime,
            Event.CloseEnrollmentDateTime closeEnrollmentDateTime,
            Event.BeginEventDateTime beginEventDateTime,
            Event.EndEventDateTime endEventDateTime,
            Event.Location location,
            Event.BasePrice basePrice,
            Event.MaxPrice maxPrice,
            Event.LimitOfEnrollment limitOfEnrollment,
            Event.Offline offline,
            Event.Free free,
            Event.Status status
    );
}
