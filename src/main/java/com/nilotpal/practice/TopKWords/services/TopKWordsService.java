package com.nilotpal.practice.TopKWords.services;

import com.nilotpal.practice.TopKWords.algorithm.TopKFrequentWords;
import com.nilotpal.practice.TopKWords.models.WordCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TopKWordsService implements TopKWordsApi{

    private static final Logger logger = LoggerFactory.getLogger(TopKWordsService.class);

    @Autowired
    private TopKFrequentWords topKFrequentWords;

    public List<WordCount> topKFrequent(Stream<String> words, int k) {

        List<WordCount> topWords = topKFrequentWords.topKFrequent(words.collect(Collectors.toList()), k);
        return topWords;
    }

    @Override
    public List<WordCount> getTopWords(int k, Map<String, Integer> wordMap) {
        return topKFrequentWords.getTopWords(k, wordMap);
    }
}
