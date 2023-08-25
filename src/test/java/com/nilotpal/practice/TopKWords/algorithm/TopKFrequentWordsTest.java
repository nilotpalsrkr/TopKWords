package com.nilotpal.practice.TopKWords.algorithm;

import com.nilotpal.practice.TopKWords.models.WordCount;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopKFrequentWordsTest {

    @Test
    void topKFrequent() {
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();

        List<String> words = Arrays.asList("apple", "apple", "banana", "apple", "cherry", "banana");
        int k = 2;

        List<WordCount> topWords = topKFrequentWords.topKFrequent(words, k);

        assertEquals(2, topWords.size());

        // Check the first top word
        WordCount firstTopWord = topWords.get(0);
        assertEquals("apple", firstTopWord.word());
        assertEquals(3, firstTopWord.count());

        // Check the second top word
        WordCount secondTopWord = topWords.get(1);
        assertEquals("banana", secondTopWord.word());
        assertEquals(2, secondTopWord.count());
    }
}