package com.nilotpal.practice.TopKWords.algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for splitting a large file into smaller chunks and managing their creation on disk.
 *
 * This class provides functionality to split a large input file into smaller chunks of specified size.
 * Chunks are created on disk and represented as separate files, enabling efficient processing of large files.
 *
 * The chunk size and output directory path are configured during the class's initialization using Spring's
 * `@Value` annotations, making the class adaptable to different configurations.
 *
 * @see FileSplitter#chunks(String) for the method that performs the file splitting and chunk creation.
 */
@Service
public class FileSplitter {

    private static final Logger logger = LoggerFactory.getLogger(FileSplitter.class);

    private int chunkSizeInBytes;

    private String outputDirPath;

    public FileSplitter(@Value("${top-k-words.file.chunk-size}") int chunkSizeInBytes, @Value("${top-k-words.file.outputDir}")String outputDirPath) {
        this.chunkSizeInBytes = chunkSizeInBytes;
        this.outputDirPath = outputDirPath;
        File outputDirFile = new File(outputDirPath);
        if (!outputDirFile.exists()) {
            if (outputDirFile.mkdirs()) {
                logger.info("Output directory created - " + outputDirPath);
            }
        }
    }

    /**
     * The Return is a chunks created on disk. We can iterate through the list of chunks one by one. We dont return List of Files, rather
     * we return just the chunk names in strings so that they can be read one by one from disk and processed.
     * @param filePath
     * @return List of chunks. This is rather a list of Strings and not List of Files.
     */
    public List<String> chunks(String filePath) {
        String inputFilePath = filePath;
        String outputFolderPath = outputDirPath;
        long chunkSize = chunkSizeInBytes * 1024 * 1024; // 1MB in bytes
        List<String> chunks = new ArrayList<>();
        try {
            File inputFile = new File(inputFilePath);
            String fileName = inputFile.getName();
            FileInputStream inputStream = new FileInputStream(inputFile);

            byte[] buffer = new byte[(int) chunkSize];
            int bytesRead;
            int chunkCounter = 1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                File outputChunk = new File(outputFolderPath + fileName + "_chunk_" + chunkCounter + ".dat");
                if (outputChunk.createNewFile()) {
                    logger.info("Chunk File created successfully!");
                } else {
                    logger.info("Chunk File already exists.");
                }

                FileOutputStream outputStream = new FileOutputStream(outputChunk);
                outputStream.write(buffer, 0, bytesRead);
                outputStream.close();
                chunks.add(outputChunk.getAbsolutePath());
                chunkCounter++;
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chunks;
    }
}

