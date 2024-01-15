package org.opennuri.study.restful.events.application.port.in;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;



@ActiveProfiles("test")
class EventCommandTest {

    public static Stream<Arguments> testFree() {
        return Stream.of(
                Arguments.of(0, 0, true),
                Arguments.of(100, 0, false),
                Arguments.of(0, 100, false)
        );
    }

    @DisplayName("basePrice와 maxPrice의 값 여부에 따라 무료 여부를 판단하는 테스트")
    @ParameterizedTest
    @MethodSource
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        // Given
        EventCommand eventCommand = EventCommand.builder()
                .name("Event")
                .description("Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2021, 8, 1, 0, 0, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2021, 8, 2, 0, 0, 0))
                .beginEventDateTime(LocalDateTime.of(2021, 8, 3, 0, 0, 0))
                .endEventDateTime(LocalDateTime.of(2021, 8, 4, 0, 0, 0))
                .location("강남역 D2 스타텁 팩토리")
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .limitOfEnrollment(100)
                .build();
        // When
        eventCommand.update();
        // Then
       assertThat(eventCommand.isFree()).isEqualTo(isFree);
    }

    public static Stream<Arguments> parametersForTestOffline() {
        return Stream.of(
                Arguments.of("강남역 D2 스타텁 팩토리", true),
                Arguments.of("", false),
                Arguments.of(" ", false),
                Arguments.of(null, false)
        );
    }
    @DisplayName("location의 값 여부에 따라 오프라인 여부를 판단하는 테스트")
    @ParameterizedTest
    @MethodSource("parametersForTestOffline")
    public void testOffline(String location, boolean isOffline) {
        // Given
        EventCommand eventCommand = EventCommand.builder()
                .name("Event")
                .description("Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2021, 8, 1, 0, 0, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2021, 8, 2, 0, 0, 0))
                .beginEventDateTime(LocalDateTime.of(2021, 8, 3, 0, 0, 0))
                .endEventDateTime(LocalDateTime.of(2021, 8, 4, 0, 0, 0))
                .location(location)
                .basePrice(0)
                .maxPrice(0)
                .limitOfEnrollment(100)
                .build();
        // When
        eventCommand.update();
        // Then
        assertThat(eventCommand.isOffline()).isEqualTo(isOffline);
    }

}