package com.nilotpal.practice.TopKWords.models;

import java.util.List;
import java.util.Map;

public record TopKResponse (int k, Map<String, Integer> topWords, String status){}
