package com.nilotpal.practice.TopKWords.controllers;


import com.nilotpal.practice.TopKWords.services.FileStoreApi;
import com.nilotpal.practice.TopKWords.services.TopKWordsApi;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import java.io.InputStream;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {
        "spring.servlet.multipart.max-file-size=500MB",
        "spring.servlet.multipart.max-request-size=10MB"
})
public class TopKWordsServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopKWordsApi topKWordsApi;

    @MockBean
    private FileStoreApi fileStoreApi;


    @Test
    @WithMockUser(username = "admin", roles = "admin")
    public void testAuthenticatedAccessToTopKWordsEndpoint() throws Exception {
        InputStream largeInputStream = getClass().getResourceAsStream("/sample-2mb-text-file.txt");
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "sample-2mb-text-file.txt",
                MediaType.TEXT_PLAIN_VALUE,
                largeInputStream.readAllBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/top-k-words")
                .file(file)
                .param("k", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.topWords").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}