package fileloader.downloaderapp.service;



import fileloader.downloaderapp.model.entity.FileEntity;



import java.util.UUID;

public interface FileService {
    FileEntity getById(UUID id);
}
