package com.nilotpal.practice.TopKWords.services;

import com.nilotpal.practice.TopKWords.algorithm.FileSplitter;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService implements FileStoreApi, FileSplitterApi{

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileSplitter fileSplitter;

    private String outputDirPath;

    @Override
    public List<String> store(MultipartFile file) throws IOException {
        File multipartFile = new File(outputDirPath + file.getName());
        if (!multipartFile.exists()) {
            FileUtils.writeByteArrayToFile(multipartFile, file.getBytes());
        }
        return chunks(multipartFile.getAbsolutePath());
    }

    @Override
    public List<String> chunks(String filePath) {
        if (!filePath.isEmpty()) {
            return fileSplitter.chunks(filePath);
        }
        return null;
    }

    @Override
    public Stream<String> readWordsFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);

        Stream<String> words;
        try (var lines = Files.lines(path)) {
            words = lines
                    .flatMap(line -> List.of(line.split("\\s+")).stream()) // Split lines into words
                    .filter(word -> !word.isEmpty()) // Remove empty words
                    ;
        }
        return words;
    }
}
