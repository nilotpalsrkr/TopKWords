package com.nilotpal.practice.TopKWords.models;

import java.io.Serializable;

public record WordCount(String word, int count) implements Serializable {
}
