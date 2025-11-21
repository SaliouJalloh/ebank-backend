package org.msd.ebankingbackend.api.dto;

public record FileUploadResponse(
        String fileName,
        String fileDownloadUri,
        String fileType, long size
) {
}
