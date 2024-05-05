package ilya.coursework.fileloader.repository;

import ilya.coursework.fileloader.model.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
}
