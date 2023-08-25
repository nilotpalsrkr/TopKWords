package com.nilotpal.practice.TopKWords.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * This interface defines the contract for a service that provides methods to store and read files,
 * as well as read words from files.
 */
public interface FileStoreApi {

    /**
     * Stores the provided MultipartFile in the system and returns a list of paths to chunks created from the stored file.
     *
     * @param file The MultipartFile to be stored
     * @return A list of paths to file chunks created from the stored file
     * @throws IOException If an I/O error occurs during file storage
     */
    List<String> store(MultipartFile file) throws IOException;

    /**
     * Reads words from the specified file path and returns them as a Stream of strings.
     *
     * @param filePath The path of the file to read words from
     * @return A Stream of words read from the file
     * @throws IOException If an I/O error occurs while reading the file
     */
    Stream<String> readWordsFromFile(String filePath) throws IOException;

}
