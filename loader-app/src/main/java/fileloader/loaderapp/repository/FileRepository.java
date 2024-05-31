package fileloader.loaderapp.repository;


import fileloader.loaderapp.model.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
    Optional<byte[]> findFileById(UUID id);
}
