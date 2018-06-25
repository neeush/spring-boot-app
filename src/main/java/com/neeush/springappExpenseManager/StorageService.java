package com.neeush.springappExpenseManager;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    Stream<Path> loadAll();

    String load(String filename);

    Resource loadAsResource(String filename);
    
    String getBatches();

    void deleteAll();

	void store(MultipartFile file, String appName, String jobName);

}
