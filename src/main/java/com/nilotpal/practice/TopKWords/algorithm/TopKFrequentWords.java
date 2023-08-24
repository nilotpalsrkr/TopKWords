package com.nilotpal.practice.TopKWords.algorithm;

import com.nilotpal.practice.TopKWords.models.WordCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TopKFrequentWords {
    private static final Logger logger = LoggerFactory.getLogger(TopKFrequentWords.class);

    public List<WordCount> topKFrequent(List<String> words, int k) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        List<WordCount> topWords = getTopWords(k, wordMap);
        return topWords;
    }

    private List<WordCount> getTopWords(int k, Map<String, Integer> wordMap) {
        PriorityQueue<String> heap = new PriorityQueue<>(
                (w1, w2) -> wordMap.get(w1).equals(wordMap.get(w2)) ? w2.compareTo(w1) : wordMap.get(w1) - wordMap.get(w2));

        for (String word : wordMap.keySet()) {
            heap.offer(word);
            if (heap.size() > k)
                heap.poll();
        }

        List<WordCount> topWords = new ArrayList<>();
        while (!heap.isEmpty()) {
            //res.add(heap.poll());
            String word = heap.poll();
            topWords.add(new WordCount(word, wordMap.get(word)));
        }
        topWords.sort(Comparator.comparingInt(WordCount::count).reversed());
        return topWords;
    }


}
