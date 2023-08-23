package com.nilotpal.practice.TopKWords.services;


import com.nilotpal.practice.TopKWords.algorithm.TopKFrequentWords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TopKWordsServiceTest {

    @Autowired
    private TopKWordsService topKWordsService;

    @MockBean
    private TopKFrequentWords topKFrequentWords;

    @Test
    public void testTopKFrequent_withFewWords() {

        List<String> words = Arrays.asList("apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog");
        int k = 2;
        List<String> expected = Arrays.asList("banana", "apple");
        when(topKFrequentWords.topKFrequent(words, k)).thenReturn(expected);
        List<String> result = topKFrequentWords.topKFrequent(words, k);
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected, result);
    }

    @Test
    public void testTopKFrequent_withManyWords() {

        List<String> words = Arrays.asList("dog", "cherry", "cat", "fog", "mapple", "are", "pqertt", "banana", "cherry",
                "banana", "apple", "dog","cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog",
                "apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple",
                "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog",
                "apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog",
                "apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog",
                "apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog");
        int k = 2;
        List<String> expected = Arrays.asList("banana", "apple");
        when(topKFrequentWords.topKFrequent(words, k)).thenReturn(expected);
        List<String> result = topKFrequentWords.topKFrequent(words, k);
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected, result);
    }
}
