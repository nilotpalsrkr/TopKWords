package com.nilotpal.practice.TopKWords.models;

import java.util.List;
import java.util.Map;

public record TopKResponse (int k, List<WordCount> topWords, String status){}
