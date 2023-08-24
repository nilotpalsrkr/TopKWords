package com.nilotpal.practice.TopKWords.controllers;

import com.nilotpal.practice.TopKWords.models.TopKResponse;
import com.nilotpal.practice.TopKWords.models.WordCount;
import com.nilotpal.practice.TopKWords.services.FileService;
import com.nilotpal.practice.TopKWords.services.FileStoreApi;
import com.nilotpal.practice.TopKWords.services.TopKWordsApi;
import org.apache.commons.io.FileUtils;
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

import java.io.File;
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
            List<String> fileChunkPaths = fileStoreApi.store(file);
            List<WordCount> r = fileChunkPaths.parallelStream().map(chunkFilePath -> {
                Stream<String> words = null;
                try {
                    words = fileStoreApi.readWordsFromFile(chunkFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return words;
            }).map(s -> topKWordsApi.topKFrequent(s , k)).flatMap(x -> x.stream())
                    .collect(Collectors.toList());
            topKWords = r.stream()
                    .collect(Collectors.toMap(WordCount::word, WordCount::count, Integer::sum));
            logger.info(String.valueOf(topKWords));

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new TopKResponse(k, topKWords, "Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new TopKResponse(k, topKWords, "Success"), HttpStatus.OK);
    }

}
