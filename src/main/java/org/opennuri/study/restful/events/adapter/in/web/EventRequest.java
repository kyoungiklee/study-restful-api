package org.opennuri.study.restful.events.adapter.in.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @NotBlank
    private  String name;
    @NotBlank
    private  String description;
    @NotNull
    private  LocalDateTime beginEnrollmentDateTime;
    @NotNull
    private  LocalDateTime closeEnrollmentDateTime;
    @NotNull
    private  LocalDateTime beginEventDateTime;
    @NotNull
    private  LocalDateTime endEventDateTime;

    private  String location;
    @Min(0)
    private  int basePrice;
    @Min(0)
    private  int maxPrice;
    @Min(0)
    private  int limitOfEnrollment;
}
