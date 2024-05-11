package ilya.coursework.fileloader.repository;

import ilya.coursework.fileloader.model.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
    Optional<byte[]> findFileById(UUID id);
}
