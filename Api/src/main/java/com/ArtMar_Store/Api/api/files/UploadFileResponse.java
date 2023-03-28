package com.ArtMar_Store.Api.api.files;

public record UploadFileResponse(String fileName,
                                 String fileDownloadUri,
                                 String fileType,
                                 Long fileSize) {
}
