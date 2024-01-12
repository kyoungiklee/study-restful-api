package org.opennuri.study.restful.events.application.port.in;

import org.opennuri.study.restful.events.domain.Event;

public interface EventUseCase {
    Event createEvent(EventCommand command);
}
