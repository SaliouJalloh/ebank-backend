package org.msd.ebankingbackend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileStorageService {

    void storeFile(byte[] content, String fileName) throws IOException;

    String storeFile(MultipartFile file) throws IOException;

    Resource loadFileAsResource(String fileName);
}
