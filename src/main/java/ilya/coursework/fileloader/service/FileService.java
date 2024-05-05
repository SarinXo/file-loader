package ilya.coursework.fileloader.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FileService {
    UUID save(MultipartFile file);

    MultipartFile getFileById(UUID id);

    List<UUID> getIdListFiles();
}
