package org.msd.ebankingbackend.application.dto.response;

public record FileUploadResponse(
        String fileName,
        String fileDownloadUri,
        String fileType, long size
) {
}
