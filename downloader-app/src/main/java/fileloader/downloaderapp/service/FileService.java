package fileloader.downloaderapp.service;


import fileloader.downloaderapp.model.dto.FileInfoProjection;
import fileloader.downloaderapp.model.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FileService {
    UUID save(MultipartFile file);
    FileEntity getById(UUID id);
    List<FileInfoProjection> getIdListFiles();
}
