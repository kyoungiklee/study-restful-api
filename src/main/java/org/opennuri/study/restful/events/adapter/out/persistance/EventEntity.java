package org.opennuri.study.restful.events.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.*;
import org.opennuri.study.restful.events.domain.EventStatus;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "EVENT")
public class EventEntity {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;

    @Enumerated(value = EnumType.STRING)
    private  EventStatus status;

    @Override
    public String toString() {
        return "EventEntity{" +
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
}
