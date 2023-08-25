package com.nilotpal.practice.TopKWords.services;

import com.nilotpal.practice.TopKWords.models.WordCount;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This interface defines the contract for a service that provides methods to compute top K frequent words.
 */
public interface TopKWordsApi {

    /**
     * Computes and returns a list of the top K most frequent words from the given Stream of words.
     * Uses caching to improve performance.
     *
     * @param words The Stream of words to analyze
     * @param k     The number of top frequent words to return
     * @return A list of WordCount objects representing the top K frequent words along with their counts
     */
    @Cacheable("topKCache")
    List<WordCount> topKFrequent(Stream<String> words, int k);

    /**
     * Computes and returns a list of the top K most frequent words based on the given word frequency map.
     *
     * @param k        The number of top frequent words to return
     * @param wordMap  Map containing word frequencies
     * @return A list of WordCount objects representing the top K frequent words along with their counts
     */

    List<WordCount> getTopWords(int k, Map<String, Integer> wordMap);
}
