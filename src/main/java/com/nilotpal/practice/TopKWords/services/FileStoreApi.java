package com.nilotpal.practice.TopKWords.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface FileStoreApi {

    List<String> store(MultipartFile file) throws IOException;
    Stream<String> readWordsFromFile(String filePath) throws IOException;

}
