package com.nilotpal.practice.TopKWords.controllers;


import com.nilotpal.practice.TopKWords.TopKResponse;
import com.nilotpal.practice.TopKWords.services.TopKWordsApi;
import org.apache.commons.io.FileUtils;
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
import java.util.List;

@RestController
public class TopKWordsController {

    @Autowired
    private TopKWordsApi topKWordsApi;

    @PostMapping("/top-k-words")
    public ResponseEntity<TopKResponse> uploadFile(
            @RequestPart("file") MultipartFile file, @RequestParam int k) {
        List<String> topKWords = null;
        try {
            File testFile = new File("test");
            FileUtils.writeByteArrayToFile(testFile, file.getBytes());
            List<String> lines = FileUtils.readLines(testFile);
            topKWords = topKWordsApi.topKFrequent(lines, k);
            System.out.println(topKWords);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<TopKResponse>(new TopKResponse(k, topKWords, "Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TopKResponse>(new TopKResponse(k, topKWords, "Success"), HttpStatus.OK);
    }

}
