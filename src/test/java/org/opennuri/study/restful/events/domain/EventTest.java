package org.opennuri.study.restful.events.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
class EventTest {
    @Test
    @DisplayName("Event 생성 테스트")
    public void construct() {
        Event event = Event.from(
                new Event.Id(1L),
                new Event.Name("Event"),
                new Event.Description("Spring"),
                new Event.BeginEnrollmentDateTime(LocalDateTime.of(2021, 8, 1, 0, 0, 0)),
                new Event.CloseEnrollmentDateTime(LocalDateTime.of(2021, 8, 2, 0, 0, 0)),
                new Event.BeginEventDateTime(LocalDateTime.of(2021, 8, 3, 0, 0, 0)),
                new Event.EndEventDateTime(LocalDateTime.of(2021, 8, 4, 0, 0, 0)),
                new Event.Location("강남역 D2 스타트업 팩토리"),
                new Event.BasePrice(0),
                new Event.MaxPrice(0),
                new Event.LimitOfEnrollment(100),
                new Event.Offline(false),
                new Event.Free(false),
                new Event.Status(EventStatus.DRAFT)
        );
        assertThat(event).isNotNull();
    }

    @Test
    @DisplayName("javaBean 테스트")
    public void javaBean() {
        // Given
        String name = "Event";
        String description = "Spring";

        // When
        Event event = Event.from(
                new Event.Id(1L),
                new Event.Name(name),
                new Event.Description(description),
                new Event.BeginEnrollmentDateTime(LocalDateTime.of(2021, 8, 1, 0, 0, 0)),
                new Event.CloseEnrollmentDateTime(LocalDateTime.of(2021, 8, 2, 0, 0, 0)),
                new Event.BeginEventDateTime(LocalDateTime.of(2021, 8, 3, 0, 0, 0)),
                new Event.EndEventDateTime(LocalDateTime.of(2021, 8, 4, 0, 0, 0)),
                new Event.Location(null),
                new Event.BasePrice(0),
                new Event.MaxPrice(0),
                new Event.LimitOfEnrollment(100),
                new Event.Offline(false),
                new Event.Free(false),
                new Event.Status(EventStatus.DRAFT)
        );

        assertThat(event).isNotNull();
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);

    }

}