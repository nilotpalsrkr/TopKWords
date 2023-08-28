package com.nilotpal.practice.TopKWords.models;

import java.io.Serializable;

/**
 * Represents a word and its corresponding count, designed for serialization
 * and storage in Redis.
 *
 * This class encapsulates a word along with its associated count, making it
 * suitable for storing word frequency information. Instances of this class
 * can be serialized and deserialized, making it convenient for use cases
 * involving data persistence and retrieval, such as storing word counts in Redis.
 *
 * @param word The word for which the count is maintained.
 * @param count The count indicating the frequency of the word.
 */
public record WordCount(String word, int count) implements Serializable {
}
