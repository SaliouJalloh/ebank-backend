package org.msd.ebankingbackend.application.dto;

public record FileUploadResponse(
        String fileName,
        String fileDownloadUri,
        String fileType, long size
) {
}
