package fileloader.downloaderapp.model.dto;


import java.util.UUID;

public record FileInfoProjection(
        UUID id,
        String fileName
) { }