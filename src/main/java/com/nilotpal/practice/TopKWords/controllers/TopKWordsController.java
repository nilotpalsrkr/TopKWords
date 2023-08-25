package com.nilotpal.practice.TopKWords.controllers;

import com.nilotpal.practice.TopKWords.models.TopKResponse;
import com.nilotpal.practice.TopKWords.models.WordCount;
import com.nilotpal.practice.TopKWords.services.FileStoreApi;
import com.nilotpal.practice.TopKWords.services.TopKWordsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TopKWordsController {

    private static final Logger logger = LoggerFactory.getLogger(TopKWordsController.class);

    @Autowired
    private FileStoreApi fileStoreApi;

    @Autowired
    private TopKWordsApi topKWordsApi;

    @PostMapping("/top-k-words")
    public ResponseEntity<TopKResponse> uploadFile(
            @RequestPart("file") MultipartFile file, @RequestParam int k) {
        Map<String, Integer> topKWords = null;
        try {
            /**
             * First the file is persisted in server, and divided into chunks. After the chunks are created this file is
             * deleted from server.
             */
            List<String> fileChunkPaths = fileStoreApi.store(file);

            /**
             * Each chunk of files (1MB) is processed in parallel and in streams. This would reduce memory footprints
             * to process the file. We dont delete the chunk of files after processing for now.
             */
            List<WordCount> topWordCountsFromChunks = fileChunkPaths.parallelStream().map(chunkFilePath -> {
                Stream<String> words = null;
                try {
                    words = fileStoreApi.readWordsFromFile(chunkFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return words;
            }).map(s -> topKWordsApi.topKFrequent(s , k)).flatMap(x -> x.stream())
                    .collect(Collectors.toList());
            /**
             * Top K words from each chunk of file is processed. Now top k words from these chunks needs to be
             * reprocessed to find out the final top K words. Here, for each word count from across the K top
             * words are summed and the top k is chosen.
             */
            topKWords = topWordCountsFromChunks.stream()
                    .collect(Collectors.toMap(WordCount::word, WordCount::count, Integer::sum));
            /*topWordCountsFromChunks.stream()
                    .collect(Collectors.toMap(
                            WordCount::word,
                            WordCount::count,
                            Integer::sum,
                            (u, v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); },
                            TreeMap::new,  // Use TreeMap as the map supplier
                            Comparator.<String, Integer>comparing(topWordCountsFromChunks::indexOf)
                                    .reversed()  // Sort by count in decreasing order
                                    .thenComparing(Comparator.naturalOrder())  // Sort by word in case of count ties
                    ));*/

            logger.info(String.valueOf(topKWords));

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new TopKResponse(k, topKWords, "Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new TopKResponse(k, topKWords, "Success"), HttpStatus.OK);
    }
}
