package com.nilotpal.practice.TopKWords.services;

import com.nilotpal.practice.TopKWords.algorithm.TopKFrequentWords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopKWordsService implements TopKWordsApi{

    private static final Logger logger = LoggerFactory.getLogger(TopKWordsService.class);

    @Autowired
    private TopKFrequentWords topKFrequentWords;

    public List<String> topKFrequent(List<String> words, int k) {

        List<String> topWords = topKFrequentWords.topKFrequent(words, k);
        return topWords;
    }
}
