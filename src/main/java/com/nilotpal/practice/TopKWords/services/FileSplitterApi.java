package com.nilotpal.practice.TopKWords.services;

import java.util.List;

/**
 * This interface defines the contract for a service that provides methods to split a file into chunks.
 */
public interface FileSplitterApi {
    /**
     * Splits a file into chunks and returns a list of paths to the created chunks.
     *
     * @param filePath The path of the file to be split
     * @return A list of paths to file chunks
     */

    List<String> chunks(String filePath);
}
