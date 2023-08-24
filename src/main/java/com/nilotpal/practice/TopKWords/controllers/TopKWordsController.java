package com.nilotpal.practice.TopKWords.controllers;

import com.nilotpal.practice.TopKWords.models.TopKResponse;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        List<String> topKWords = null;
        try {
            List<String> fileChunkPaths = fileStoreApi.store(file);
            List<String> r = fileChunkPaths.parallelStream().map(chunkFilePath -> {
                Stream<String> words = null;
                try {
                    words = fileStoreApi.readWordsFromFile(chunkFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return words;
            }).map(stream -> topKWordsApi.topKFrequent(stream.collect(Collectors.toList()), k)).flatMap(x -> x.stream())
                    .collect(Collectors.toList());
            Collections.sort(r);
            //topKWords = topKWordsApi.topKFrequent(lines, k);
            topKWords = r.stream().limit(k).collect(Collectors.toList());
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new TopKResponse(k, topKWords, "Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new TopKResponse(k, topKWords, "Success"), HttpStatus.OK);
    }

}
