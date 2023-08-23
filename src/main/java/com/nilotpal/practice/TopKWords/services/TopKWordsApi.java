package com.nilotpal.practice.TopKWords.services;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface TopKWordsApi {

    @Cacheable("topKCache")
    List<String> topKFrequent(List<String> words, int k);
}
