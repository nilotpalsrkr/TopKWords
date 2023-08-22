package com.nilotpal.practice.TopKWords.controllers;

import com.nilotpal.practice.TopKWords.services.TopKWordsApi;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import java.io.InputStream;

@WebMvcTest(TopKWordsController.class)
@TestPropertySource(properties = {
        "spring.servlet.multipart.max-file-size=500MB",
        "spring.servlet.multipart.max-request-size=10MB"
})
public class TopKWordsServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopKWordsApi topKWordsApi;

    @Test
    public void testUploadFile() throws Exception {

        InputStream largeInputStream = getClass().getResourceAsStream("/sample-2mb-text-file.txt");
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "sample-2mb-text-file.txt",
                MediaType.TEXT_PLAIN_VALUE,
                largeInputStream.readAllBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/top-k-words")
                .file(file)
                .param("k", "2")
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.topWords").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}