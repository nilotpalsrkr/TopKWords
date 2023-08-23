package com.nilotpal.practice.TopKWords.algorithm;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class LargeFileDegeneratorTest {

    @Test
    public void testChunksMethod() throws IOException {
        // Set up test data
        int chunkSizeInMB = 1;
        String outputDirPath = "src/test/resources/large_files_output/";
        String inputFolderPath = "src/test/resources/large_files_input/";
        String inputFileName = "sample-2mb-text-file.txt";

        // Create an instance of LargeFileDegenerator
        LargeFileDegenerator degenerator = new LargeFileDegenerator(chunkSizeInMB, outputDirPath);

        // Run the chunks method
        degenerator.chunks(inputFolderPath + inputFileName);

        // Check if the expected output files are generated
        File outputDir = new File(outputDirPath);
        File[] outputFiles = outputDir.listFiles();
        assertNotNull(outputFiles);
        assertEquals(1, outputFiles.length);

        // Check the content of the generated output file
        FileInputStream inputStream = new FileInputStream(outputFiles[0]);
        byte[] buffer = new byte[(int) outputFiles[0].length()];
        inputStream.read(buffer);
        inputStream.close();

        // You can add additional assertions here to compare the output with expected content
    }
}
