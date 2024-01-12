package org.opennuri.study.restful.events.adapter.out.persistance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opennuri.study.restful.common.annotation.PersistenceAdapter;
import org.opennuri.study.restful.events.adapter.out.persistance.repository.SpringJpaEventRepository;
import org.opennuri.study.restful.events.application.port.out.EventPort;
import org.opennuri.study.restful.events.domain.Event;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class EventAdapter implements EventPort {
    private final SpringJpaEventRepository springJpaEventRepository;
    @Override
    public Event saveEvent(
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
    ) {
        EventEntity save = springJpaEventRepository.save(
                EventEntity.builder()
                        .name(name.name())
                        .description(description.description())
                        .beginEnrollmentDateTime(beginEnrollmentDateTime.beginEnrollmentDateTime())
                        .closeEnrollmentDateTime(closeEnrollmentDateTime.closeEnrollmentDateTime())
                        .beginEventDateTime(beginEventDateTime.beginEventDateTime())
                        .endEventDateTime(endEventDateTime.endEventDateTime())
                        .location(location.location())
                        .basePrice(basePrice.basePrice())
                        .maxPrice(maxPrice.maxPrice())
                        .limitOfEnrollment(limitOfEnrollment.limitOfEnrollment())
                        .offline(offline.offline())
                        .free(free.free())
                        .status(status.status())
                        .build()
        );

        log.info("EventAdapter.saveEvent() is called. save: {}", save);
        return EventMapper.mapToDomainEntity(save);
    }
}
