package com.nilotpal.practice.TopKWords.services;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopKWordsServiceTest {

    @Test
    public void testTopKFrequent_withFewWords() {
        TopKWordsService topKFrequentWords = new TopKWordsService();

        List<String> words = Arrays.asList("apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog");
        int k = 2;
        List<String> expected = Arrays.asList("banana", "apple");

        List<String> result = topKFrequentWords.topKFrequent(words, k);
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected, result);
    }

    @Test
    public void testTopKFrequent_withManyWords() {
        TopKWordsService topKFrequentWords = new TopKWordsService();

        List<String> words = Arrays.asList("dog", "cherry", "cat", "fog", "mapple", "are", "pqertt", "banana", "cherry",
                "banana", "apple", "dog","cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog",
                "apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple",
                "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog",
                "apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog",
                "apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog",
                "apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog","apple", "banana", "apple", "banana", "cherry", "banana", "apple", "dog");
        int k = 2;
        List<String> expected = Arrays.asList("banana", "apple");

        List<String> result = topKFrequentWords.topKFrequent(words, k);
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected, result);
    }
}
