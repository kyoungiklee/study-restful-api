package org.opennuri.study.restful.events.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "id")
public class Event {
    private final Long id; // 이벤트 아이디
    private final String name; // 이벤트 이름
    private final String description; // 이벤트 설명
    private final LocalDateTime beginEnrollmentDateTime; // 이벤트 등록 시작일
    private final LocalDateTime closeEnrollmentDateTime; // 이벤트 등록 종료일
    private final LocalDateTime beginEventDateTime; // 이벤트 시작일
    private final LocalDateTime endEventDateTime; // 이벤트 종료일
    private final String location; // (optional) 값이 없으면 온라인 모임

    // basePrice와 maxPrice가 0이면 무료
    // basePrice가 0이고 maxPrice가 0이 아니면 선착순
    // basePrice가 0이 아니고 maxPrice가 0이면 무제한 경매(높은 금액을 낸 사람이 등록됨)
    // basePrice가 0이 아니고 maxPrice가 0이 아니면 제한가 선착순(처음부터 maxPrice를 낸 사람이 등록됨, basePrice로 낸 사람도 등록되나인원이 넘어 갔을 경우 maxPrice로 낸 사람이 등록됨)
    private final int basePrice; // (optional)
    private final int maxPrice; // (optional)

    private final int limitOfEnrollment; // 등록한 참가자 수
    private final boolean offline; // 오프라인 여부
    private final boolean free; // 무료 여부
    private final EventStatus status; // 이벤트 상태 (DRAFT, PUBLISHED, BEGAN_ENROLLMENT)

    public static Event from(
            Event.Id id,
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
        return new Event(
                id.id(),
                name.name(),
                description.description(),
                beginEnrollmentDateTime.beginEnrollmentDateTime(),
                closeEnrollmentDateTime.closeEnrollmentDateTime(),
                beginEventDateTime.beginEventDateTime(),
                endEventDateTime.endEventDateTime(),
                location.location(),
                basePrice.basePrice(),
                maxPrice.maxPrice(),
                limitOfEnrollment.limitOfEnrollment(),
                offline.offline(),
                free.free(),
                status.status()
        );
    }

    public record Id(Long id) {
        public static Id of(Long id) {
            return new Id(Objects.requireNonNull(id));
        }
    }
    public record Name(String name) {
        public static Name of(String name) {
            return new Name(Objects.requireNonNull(name));
        }
    }
    public record Description(String description) {
        public static Description of(String description) {
            return new Description(Objects.requireNonNull(description));
        }
    }
    public record BeginEnrollmentDateTime(LocalDateTime beginEnrollmentDateTime) {
        public static BeginEnrollmentDateTime of(LocalDateTime beginEnrollmentDateTime) {
            return new BeginEnrollmentDateTime(Objects.requireNonNull(beginEnrollmentDateTime));
        }
    }
    public record CloseEnrollmentDateTime(LocalDateTime closeEnrollmentDateTime) {
        public static CloseEnrollmentDateTime of(LocalDateTime closeEnrollmentDateTime) {
            return new CloseEnrollmentDateTime(Objects.requireNonNull(closeEnrollmentDateTime));
        }
    }
    public record BeginEventDateTime(LocalDateTime beginEventDateTime) {
        public static BeginEventDateTime of(LocalDateTime beginEventDateTime) {
            return new BeginEventDateTime(Objects.requireNonNull(beginEventDateTime));
        }
    }
    public record EndEventDateTime(LocalDateTime endEventDateTime) {
        public static EndEventDateTime of(LocalDateTime endEventDateTime) {
            return new EndEventDateTime(Objects.requireNonNull(endEventDateTime));
        }
    }
    public record Location(String location) {
        public static Location of(String location) {
            return new Location(Objects.requireNonNull(location));
        }
    }
    public record BasePrice(int basePrice) {
        public static BasePrice of(int basePrice) {
            if (basePrice < 0) {
                throw new IllegalArgumentException("basePrice is less than 0");
            }
            return new BasePrice(basePrice);
        }
    }
    public record MaxPrice(int maxPrice) {
        public static MaxPrice of(int maxPrice) {
            if (maxPrice < 0) {
                throw new IllegalArgumentException("maxPrice is less than 0");
            }
            return new MaxPrice(maxPrice);
        }
    }
    public record LimitOfEnrollment(int limitOfEnrollment) {
        public static LimitOfEnrollment of(int limitOfEnrollment) {
            if (limitOfEnrollment < 0) {
                throw new IllegalArgumentException("limitOfEnrollment is less than 0");
            }
            return new LimitOfEnrollment(limitOfEnrollment);
        }
    }
    public record Offline(boolean offline) {
        public static Offline of(boolean offline) {
            return new Offline(offline);
        }
    }
    public record Free(boolean free) {
        public static Free of(boolean free) {
            return new Free(free);
        }
    }
    public record Status(EventStatus status) {
        public static Status of(EventStatus status) {
            return new Status(Objects.requireNonNull(status));
        }
    }


}
