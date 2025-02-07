package ru.baddecision.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class FileServiceTest {

    private final FileService fileService = new FileService();

    @Test
    void saveFile_mustReturnNull() {
        String result = fileService.saveFile(null, "/");
        assertNull(result);
    }
}