package ilya.coursework.fileloader.service;

import ilya.coursework.fileloader.model.dto.FileInfoProjection;
import ilya.coursework.fileloader.model.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileService {
    UUID save(MultipartFile file);
    FileEntity getById(UUID id);
    List<FileInfoProjection> getIdListFiles();
}
