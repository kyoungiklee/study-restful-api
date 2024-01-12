package org.opennuri.study.restful.events.adapter.out.persistance;

import org.opennuri.study.restful.events.domain.Event;

public class EventMapper {
    public static Event mapToDomainEntity(EventEntity eventJpaEntity) {
        return Event.from(
                new Event.Id(eventJpaEntity.getId()),
                new Event.Name(eventJpaEntity.getName()),
                new Event.Description(eventJpaEntity.getDescription()),
                new Event.BeginEnrollmentDateTime(eventJpaEntity.getBeginEnrollmentDateTime()),
                new Event.CloseEnrollmentDateTime(eventJpaEntity.getCloseEnrollmentDateTime()),
                new Event.BeginEventDateTime(eventJpaEntity.getBeginEventDateTime()),
                new Event.EndEventDateTime(eventJpaEntity.getEndEventDateTime()),
                new Event.Location(eventJpaEntity.getLocation()),
                new Event.BasePrice(eventJpaEntity.getBasePrice()),
                new Event.MaxPrice(eventJpaEntity.getMaxPrice()),
                new Event.LimitOfEnrollment(eventJpaEntity.getLimitOfEnrollment()),
                new Event.Offline(eventJpaEntity.isOffline()),
                new Event.Free(eventJpaEntity.isFree()),
                new Event.Status(eventJpaEntity.getStatus())
        );
    }
}
