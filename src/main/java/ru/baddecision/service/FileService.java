package ru.baddecision.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    @Value("${blog.imagesDir}")
    private String imagesDir;
    @Value("${blog.basePath}")
    private String basePath;

    public String saveFile(MultipartFile file) {
        if (file == null || StringUtils.isEmpty(file.getOriginalFilename())) {
            return null;
        }

        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        String filePath = String.format("%s/%s/%s", basePath, imagesDir, fileName);
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            log.error(String.format("Error while saving file %s", file.getOriginalFilename()), e);
            throw new RuntimeException(e);
        }
        return fileName;
    }
}