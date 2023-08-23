package com.nilotpal.practice.TopKWords.models;

import java.util.List;

public record TopKResponse (int k, List<String> topWords, String status){}
