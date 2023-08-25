package com.nilotpal.practice.TopKWords.services;

import com.nilotpal.practice.TopKWords.algorithm.FileSplitter;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileService implements FileStoreApi, FileSplitterApi{

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileSplitter fileSplitter;

    @Value("${top-k-words.file.outputDir}")
    private String outputDirPath;

    /**
     * Overrides the store method of a file storage service. Stores the uploaded MultipartFile
     * in the specified output directory and returns a list of chunks created from the stored file.
     *
     * @param file The MultipartFile to be stored
     * @return A list of paths to chunks created from the stored file
     * @throws IOException If an I/O error occurs while storing the file
     */
    @Override
    public List<String> store(MultipartFile file) throws IOException {
        File outputDirFile = new File(outputDirPath);
        if (!outputDirFile.exists()) {
            if (outputDirFile.mkdirs()) {
                logger.info("Output directory created - " + outputDirPath);
            }
        }
        File multipartFile = new File(outputDirPath + File.separator + file.getOriginalFilename());
        if (!multipartFile.exists()) {
            FileUtils.writeByteArrayToFile(multipartFile, file.getBytes());
        }
        return chunks(multipartFile.getAbsolutePath());
    }

    /**
     * Overrides the 'chunks' method to provide a list of paths to file chunks created from the given file path.
     *
     * @param filePath The path of the file to be split into chunks
     * @return A list of paths to file chunks
     */
    @Override
    public List<String> chunks(String filePath) {
        if (!filePath.isEmpty()) {
            return fileSplitter.chunks(filePath);
        }
        return null;
    }

    /**
     * Overrides the 'readWordsFromFile' method to read words from the given file path and return them as a Stream.
     *
     * @param filePath The path of the file to read words from
     * @return A Stream of non-empty words read from the file
     * @throws IOException If an I/O error occurs while reading the file
     */

    @Override
    public Stream<String> readWordsFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);

        Stream<String> words;
        Stream<String> lines = Files.lines(path);
            words = lines
                    .flatMap(line -> List.of(line.split("\\s+")).stream()) // Split lines into words
                    .filter(word -> !word.isEmpty()) // Remove empty words
                    ;

        return words;
    }
}
