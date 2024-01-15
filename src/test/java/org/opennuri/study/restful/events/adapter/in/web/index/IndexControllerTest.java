package org.opennuri.study.restful.events.adapter.in.web.index;


import org.junit.jupiter.api.Test;
import org.opennuri.study.restful.common.RestDocsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs // Spring REST Docs 사용
@Import( {
        RestDocsConfiguration.class
}) // Spring REST Docs 사용
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void index() throws Exception {
        // Given
        mockMvc.perform(get("/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.events").exists());
        // When

        // Then
    }
}
