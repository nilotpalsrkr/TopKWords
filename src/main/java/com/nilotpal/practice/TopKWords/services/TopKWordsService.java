package com.nilotpal.practice.TopKWords.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopKWordsService implements TopKWordsApi{

    public List<String> topKFrequent(List<String> words, int k) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> h = new PriorityQueue<>(
                (w1, w2) -> wordMap.get(w1).equals(wordMap.get(w2)) ? w2.compareTo(w1) : wordMap.get(w1) - wordMap.get(w2));

        for (String word : wordMap.keySet()) {
            h.offer(word);
            if (h.size() > k)
                h.poll();
        }

        List<String> res = new ArrayList<>();
        while (!h.isEmpty())
            res.add(h.poll());
        Collections.reverse(res);
        return res;
    }
}
