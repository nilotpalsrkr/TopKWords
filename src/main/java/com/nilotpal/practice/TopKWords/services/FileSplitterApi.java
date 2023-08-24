package com.nilotpal.practice.TopKWords.services;

import java.util.List;

public interface FileSplitterApi {
    List<String> chunks(String filePath);
}
