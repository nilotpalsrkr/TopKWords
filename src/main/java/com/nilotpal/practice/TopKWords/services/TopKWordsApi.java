package com.nilotpal.practice.TopKWords.services;

import com.nilotpal.practice.TopKWords.models.WordCount;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface TopKWordsApi {

    @Cacheable("topKCache")
    List<WordCount> topKFrequent(Stream<String> words, int k);

    List<WordCount> getTopWords(int k, Map<String, Integer> wordMap);
}
