package org.msd.ebankingbackend.controller.dtos;

public record FileUploadResponse(
        String fileName,
        String fileDownloadUri,
        String fileType, long size
) {
}
