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

    /**
     * Returns a list of the top K most frequent words from the given Stream of words.
     *
     * @param words The Stream of words to analyze
     * @param k     The number of top frequent words to return
     * @return A list of WordCount objects representing the top K frequent words along with their counts
     */
    public List<WordCount> topKFrequent(Stream<String> words, int k) {

        List<WordCount> topWords = topKFrequentWords.topKFrequent(words.collect(Collectors.toList()), k);
        return topWords;
    }

    /**
     * Overrides the 'getTopWords' method to provide a list of the top K most frequent words.
     *
     * @param k        The number of top frequent words to return
     * @param wordMap  Map containing word frequencies
     * @return A list of WordCount objects representing the top K frequent words along with their counts
     */
    @Override
    public List<WordCount> getTopWords(int k, Map<String, Integer> wordMap) {
        return topKFrequentWords.getTopWords(k, wordMap);
    }
}
