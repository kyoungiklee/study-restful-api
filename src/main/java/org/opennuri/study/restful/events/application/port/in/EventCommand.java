package org.opennuri.study.restful.events.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.opennuri.study.restful.common.SelfValidating;
import org.opennuri.study.restful.events.domain.EventStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter @Getter
@Builder
public class EventCommand extends SelfValidating<EventCommand> {

    private  Long id;
    @NotNull @NotBlank
    private  String name;
    private  String description;
    @NotNull
    private  LocalDateTime beginEnrollmentDateTime;
    @NotNull
    private  LocalDateTime closeEnrollmentDateTime;
    @NotNull
    private  LocalDateTime beginEventDateTime;
    @NotNull
    private  LocalDateTime endEventDateTime;
    private  String location; // (optional) 이게 없으면 온라인 모임
    private  int basePrice; // (optional)
    private  int maxPrice; // (optional)
    private  int limitOfEnrollment;
    private  boolean offline;
    private  boolean free;
    private  EventStatus status;

    public EventCommand(Long id
            , String name
            , String description
            , LocalDateTime beginEnrollmentDateTime
            , LocalDateTime closeEnrollmentDateTime
            , LocalDateTime beginEventDateTime
            , LocalDateTime endEventDateTime
            , String location
            , int basePrice
            , int maxPrice
            , int limitOfEnrollment
            , boolean offline
            , boolean free
            , EventStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.beginEnrollmentDateTime = beginEnrollmentDateTime;
        this.closeEnrollmentDateTime = closeEnrollmentDateTime;
        this.beginEventDateTime = beginEventDateTime;
        this.endEventDateTime = endEventDateTime;
        this.location = location;
        this.basePrice = basePrice;
        this.maxPrice = maxPrice;
        this.limitOfEnrollment = limitOfEnrollment;
        this.offline = offline;
        this.free = free;
        this.status = status;
        this.validateSelf();
    }

    @Override
    public String toString() {
        return "EventCommand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", beginEnrollmentDateTime=" + beginEnrollmentDateTime +
                ", closeEnrollmentDateTime=" + closeEnrollmentDateTime +
                ", beginEventDateTime=" + beginEventDateTime +
                ", endEventDateTime=" + endEventDateTime +
                ", location='" + location + '\'' +
                ", basePrice=" + basePrice +
                ", maxPrice=" + maxPrice +
                ", limitOfEnrollment=" + limitOfEnrollment +
                ", offline=" + offline +
                ", free=" + free +
                ", status=" + status +
                '}';
    }

    public void update() {
        this.free = this.basePrice == 0 && this.maxPrice == 0;
        this.offline = this.location != null && !this.location.isBlank();
    }
}
