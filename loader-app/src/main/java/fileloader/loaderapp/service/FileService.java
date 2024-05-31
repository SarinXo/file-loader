package fileloader.loaderapp.service;


import fileloader.loaderapp.model.dto.FileInfoProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FileService {
    UUID save(MultipartFile file);

    List<FileInfoProjection> getIdListFiles();

}
