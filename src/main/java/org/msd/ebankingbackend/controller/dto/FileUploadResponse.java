package org.msd.ebankingbackend.controller.dto;

public record FileUploadResponse(
        String fileName,
        String fileDownloadUri,
        String fileType, long size
) {
}
