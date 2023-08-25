package com.nilotpal.practice.TopKWords.services;


import com.nilotpal.practice.TopKWords.algorithm.TopKFrequentWords;
import com.nilotpal.practice.TopKWords.models.WordCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class TopKWordsServiceTest {

    @Autowired
    private TopKWordsService topKWordsService;

    @MockBean
    private TopKFrequentWords topKFrequentWords;

    @Test
    public void testTopKFrequent_withFewWords() {

        List<String> words = Arrays.asList("apple", "banana", "apple", "banana", "mango", "banana", "apple", "zebra");
        int k = 2;
        List<WordCount> expected = Arrays.asList(new WordCount("banana", 3), new WordCount("apple", 3));
        when(topKFrequentWords.topKFrequent(words, k)).thenReturn(expected);
        List<WordCount> result = topKFrequentWords.topKFrequent(words, k);
        assertEquals(expected, result);
    }
}
