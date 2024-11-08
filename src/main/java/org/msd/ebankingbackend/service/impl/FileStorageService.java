package org.msd.ebankingbackend.service.impl;

import org.msd.ebankingbackend.config.FileStorageProperties;
import org.msd.ebankingbackend.exception.FileNotFoundException;
import org.msd.ebankingbackend.service.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


@Service
//@RequiredArgsConstructor
public class FileStorageService implements IFileStorageService {

    private final Path fileStoragePath;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) throws IOException {
        this.fileStoragePath = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        Files.createDirectories(this.fileStoragePath);
    }

    @Override
    public void storeFile(byte[] content, String fileName) throws IOException {
        Path filePath = fileStoragePath.resolve(fileName);
        Files.write(filePath, content);
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = fileStoragePath.resolve(fileName);
        byte[] content = file.getBytes();
        storeFile(content, filePath.getFileName().toString());
        return fileName;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        Path filePath = this.fileStoragePath.resolve(fileName).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new FileNotFoundException("File not found: " + fileName);
            }
            return resource;
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found: " + fileName, ex);
        }
    }
}
