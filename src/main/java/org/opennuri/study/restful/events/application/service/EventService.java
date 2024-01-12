package org.opennuri.study.restful.events.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opennuri.study.restful.common.annotation.UseCase;
import org.opennuri.study.restful.events.application.port.in.EventCommand;
import org.opennuri.study.restful.events.application.port.in.EventUseCase;
import org.opennuri.study.restful.events.application.port.out.EventPort;
import org.opennuri.study.restful.events.domain.Event;
import org.opennuri.study.restful.events.domain.EventStatus;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class EventService implements EventUseCase {
    private final EventPort eventPort;

    @Override
    public Event createEvent(EventCommand command) {
        log.info("EventService.createEvent() is called. command: {}", command);

        return eventPort.saveEvent(
                Event.Name.of(command.getName()),
                Event.Description.of(command.getDescription()),
                Event.BeginEnrollmentDateTime.of(command.getBeginEnrollmentDateTime()),
                Event.CloseEnrollmentDateTime.of(command.getCloseEnrollmentDateTime()),
                Event.BeginEventDateTime.of(command.getBeginEventDateTime()),
                Event.EndEventDateTime.of(command.getEndEventDateTime()),
                Event.Location.of(command.getLocation()),
                Event.BasePrice.of(command.getBasePrice()),
                Event.MaxPrice.of(command.getMaxPrice()),
                Event.LimitOfEnrollment.of(command.getLimitOfEnrollment()),
                Event.Offline.of(command.isOffline()),
                Event.Free.of(command.isFree()),
                Event.Status.of(EventStatus.DRAFT)
        );
    }
}
