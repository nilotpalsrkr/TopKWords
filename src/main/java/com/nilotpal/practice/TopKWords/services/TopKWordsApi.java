package com.nilotpal.practice.TopKWords.services;

import java.util.List;

public interface TopKWordsApi {

    List<String> topKFrequent(List<String> words, int k);
}
