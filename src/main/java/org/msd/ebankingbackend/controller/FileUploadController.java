package org.msd.ebankingbackend.controller;

/*
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor

public class FileUploadController {

    private final IFileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new FileUploadResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
}
*/