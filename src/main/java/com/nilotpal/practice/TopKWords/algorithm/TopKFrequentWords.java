package com.nilotpal.practice.TopKWords.algorithm;

import com.nilotpal.practice.TopKWords.models.WordCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Component class responsible for computing the top K most frequent words from a given list of words or a word frequency map.
 *
 * This component provides methods to calculate the top K most frequent words based on their occurrence in a list of words or
 * a word frequency map. It employs a priority queue (heap) to efficiently identify and maintain the top K frequent words.
 * The class is designed to encapsulate the logic for extracting and processing frequent words, making it a reusable and modular
 * component in applications involving word frequency analysis.
 *
 * @see TopKFrequentWords#topKFrequent(List, int) for the method to compute top K frequent words from a list of words.
 * @see TopKFrequentWords#getTopWords(int, Map) for the method to compute top K frequent words from a word frequency map.
 */
@Component
public class TopKFrequentWords {
    private static final Logger logger = LoggerFactory.getLogger(TopKFrequentWords.class);

    /**
     * Given a list of words, this method returns a list of the top K most frequent words.
     *
     * @param words List of input words
     * @param k     Number of top frequent words to return
     * @return List of WordCount objects representing the top K frequent words along with their counts
     */
    public List<WordCount> topKFrequent(List<String> words, int k) {
        Map<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        List<WordCount> topWords = getTopWords(k, wordMap);
        return topWords;
    }

    /**
     * Given a map of word frequencies, this method returns a list of the top K most frequent words.
     * It uses a priority queue (heap) to efficiently find and maintain the top K frequent words.
     *
     * The below method is made public.Its usage is also for merging of top K words from multiple chunks.
     * Chunk processing would return Top frequent words map and would re-use this method to finalize the
     * top-k words.
     *
     * @param k        Number of top frequent words to return
     * @param wordMap  Map containing word frequencies
     * @return List of WordCount objects representing the top K frequent words along with their counts
     */
    public List<WordCount> getTopWords(int k, Map<String, Integer> wordMap) {
        PriorityQueue<String> heap = new PriorityQueue<>(
                (w1, w2) -> wordMap.get(w1).equals(wordMap.get(w2)) ? w2.compareTo(w1) : wordMap.get(w1) - wordMap.get(w2));

        for (String word : wordMap.keySet()) {
            heap.offer(word);
            if (heap.size() > k)
                heap.poll();
        }

        List<WordCount> topWords = new ArrayList<>();
        while (!heap.isEmpty()) {
            String word = heap.poll();
            topWords.add(new WordCount(word, wordMap.get(word)));
        }
        topWords.sort(Comparator.comparingInt(WordCount::count).reversed());
        return topWords;
    }


}
