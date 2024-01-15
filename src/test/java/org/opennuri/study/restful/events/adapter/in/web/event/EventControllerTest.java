package org.opennuri.study.restful.events.adapter.in.web.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opennuri.study.restful.common.RestDocsConfiguration;
import org.opennuri.study.restful.events.adapter.in.web.event.dto.EventRequest;
import org.opennuri.study.restful.events.domain.Event;
import org.opennuri.study.restful.events.domain.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs // Spring REST Docs 사용
@Import( {
        RestDocsConfiguration.class
}) // Spring REST Docs 사용
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("정상적으로 이벤트를 생성하는 테스트")
    public void createEvent() throws Exception {

        EventRequest request = EventRequest.builder()
                .name("Event")
                .description("Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2021, 8, 1, 0, 0, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2021, 8, 2, 0, 0, 0))
                .beginEventDateTime(LocalDateTime.of(2021, 8, 3, 0, 0, 0))
                .endEventDateTime(LocalDateTime.of(2021, 8, 4, 0, 0, 0))
                .location("강남역 D2 스타텁 팩토리")
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .build();

        System.out.println(objectMapper.writeValueAsString(request));

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) // JSON
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ) // HAL (Hypertext Application Language) : RESTful API를 위한 JSON 기반의 데이터 포맷
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,  MediaTypes.HAL_JSON_VALUE + ";charset=UTF-8"))
                .andExpect(jsonPath("id").value(Matchers.not(100L)))
                .andExpect(jsonPath("free").value(false))
                .andExpect(jsonPath("offline").value(true))
                .andExpect(jsonPath("status").value(EventStatus.DRAFT.name()))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-events").exists())
                .andExpect(jsonPath("_links.update-event").exists())
                .andDo(document("create-event",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-events").description("link to query events"),
                                linkWithRel("update-event").description("link to update an existing event"),
                                linkWithRel("profile").description("link to profile")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("name").description("Name of new event"),
                                fieldWithPath("description").description("Description of new event"),
                                fieldWithPath("beginEnrollmentDateTime").description("Date time of begin of new event"),
                                fieldWithPath("closeEnrollmentDateTime").description("Date time of close of new event"),
                                fieldWithPath("beginEventDateTime").description("Date time of begin of new event"),
                                fieldWithPath("endEventDateTime").description("Date time of end of new event"),
                                fieldWithPath("location").description("Location of new event"),
                                fieldWithPath("basePrice").description("Base price of new event"),
                                fieldWithPath("maxPrice").description("Max price of new event"),
                                fieldWithPath("limitOfEnrollment").description("Limit of enrollment of new event")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("Id of new event"),
                                fieldWithPath("name").description("Name of new event"),
                                fieldWithPath("description").description("Description of new event"),
                                fieldWithPath("beginEnrollmentDateTime").description("Date time of begin of new event"),
                                fieldWithPath("closeEnrollmentDateTime").description("Date time of close of new event"),
                                fieldWithPath("beginEventDateTime").description("Date time of begin of new event"),
                                fieldWithPath("endEventDateTime").description("Date time of end of new event"),
                                fieldWithPath("location").description("Location of new event"),
                                fieldWithPath("basePrice").description("Base price of new event"),
                                fieldWithPath("maxPrice").description("Max price of new event"),
                                fieldWithPath("limitOfEnrollment").description("Limit of enrollment of new event"),
                                fieldWithPath("offline").description("It tells if this event is offline or not"),
                                fieldWithPath("free").description("It tells if this event is free or not"),
                                fieldWithPath("status").description("Event status"),
                                fieldWithPath("_links.self.href").description("link to self"),
                                fieldWithPath("_links.query-events.href").description("link to query events"),
                                fieldWithPath("_links.update-event.href").description("link to update an existing event"),
                                fieldWithPath("_links.profile.href").description("link to profile")
                        )
                )); // Spring REST Docs 사용
    }

    @Test
    @DisplayName("입력 받을 수 없는 값을 사용한 경우에 에러가 발생하는 테스트")
    public void createEvent_bad_request() throws Exception {

        Event event = Event.from(
                new Event.Id(100L),
                new Event.Name("Event"),
                new Event.Description("Spring"),
                new Event.BeginEnrollmentDateTime(LocalDateTime.of(2021, 8, 1, 0, 0, 0)),
                new Event.CloseEnrollmentDateTime(LocalDateTime.of(2021, 8, 2, 0, 0, 0)),
                new Event.BeginEventDateTime(LocalDateTime.of(2021, 8, 3, 0, 0, 0)),
                new Event.EndEventDateTime(LocalDateTime.of(2021, 8, 4, 0, 0, 0)),
                new Event.Location("강남역 D2 스타텁 팩토리"),
                new Event.BasePrice(100),
                new Event.MaxPrice(200),
                new Event.LimitOfEnrollment(100), // 100명까지만 등록 가능
                new Event.Offline(false),
                new Event.Free(false),
                new Event.Status(EventStatus.PUBLISHED)
        );

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) // JSON
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event))
                ) // HAL (Hypertext Application Language) : RESTful API를 위한 JSON 기반의 데이터 포맷
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @DisplayName("입력 값이 비어있는 경우에 에러가 발생하는 테스트")
    public void createEvent_bad_request_empty_input() throws Exception {

        EventRequest request = EventRequest.builder().build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) // JSON
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ) // HAL (Hypertext Application Language) : RESTful API를 위한 JSON 기반의 데이터 포맷
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @DisplayName("입력 값이 잘못된 경우에 에러가 발생하는 테스트")
    public void createEvent_bad_request_wrong_input() throws Exception {

        EventRequest request = EventRequest.builder()
                .name("Event")
                .description("Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2021, 8, 1, 0, 0, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2021, 8, 2, 0, 0, 0))
                .beginEventDateTime(LocalDateTime.of(2021, 8, 3, 0, 0, 0))
                .endEventDateTime(LocalDateTime.of(2021, 8, 2, 0, 0, 0)) // 시작일자가 종료일자보다 늦은 경우
                .location("강남역 D2 스타텁 팩토리")
                .basePrice(10000) // 최대가격보다 큰 경우
                .maxPrice(200)
                .limitOfEnrollment(100)
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) // JSON
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ) // HAL (Hypertext Application Language) : RESTful API를 위한 JSON 기반의 데이터 포맷
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors[0].objectName").exists())
                .andExpect(jsonPath("errors[0].field").exists())
                .andExpect(jsonPath("errors[0].defaultMessage").exists())
                .andExpect(jsonPath("errors[0].code").exists())
                .andExpect(jsonPath("errors[0].rejectedValue").exists())
                .andExpect(jsonPath("_links.index").exists()) // 오류가 발생한 경우 이동할 수 있는 경로 정보를 제공한다.
        ;
    }

}