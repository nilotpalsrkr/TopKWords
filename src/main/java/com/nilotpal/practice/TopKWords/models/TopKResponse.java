package com.nilotpal.practice.TopKWords.models;

import java.util.List;

/**
 * Represents a response containing the top K words along with their counts and a status indicator.
 *
 * This record encapsulates a response to a query for the top K words based on their counts.
 * It includes the requested value of K, a list of WordCount objects representing the top words
 * and their associated counts, and a status indicator providing additional context about the response.
 *
 * @param k The desired number of top words to retrieve.
 * @param topWords A list of WordCount objects representing the top words and their counts.
 * @param status A status indicator providing information about the response, such as success or error.
 */
public record TopKResponse (int k, List<WordCount> topWords, String status){}
