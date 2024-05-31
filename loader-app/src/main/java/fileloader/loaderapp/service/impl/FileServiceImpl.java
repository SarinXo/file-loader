package fileloader.loaderapp.service.impl;

import fileloader.loaderapp.model.dto.FileInfoProjection;
import fileloader.loaderapp.model.entity.FileEntity;
import fileloader.loaderapp.repository.FileRepository;
import fileloader.loaderapp.service.FileService;
import fileloader.loaderapp.util.ZipUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @PersistenceContext
    private final EntityManager em;
    private final FileRepository fileRepository;

    @Override
    public UUID save(MultipartFile file) {
        try {
            val zipArchive = ZipUtil.zip(file.getBytes());
            val fileEntity = new FileEntity(zipArchive, file.getOriginalFilename());
            return fileRepository.save(fileEntity).getId();
        } catch (IOException e) {
            throw new RuntimeException("Cannot save file");
        }
    }

    @Override
    public List<FileInfoProjection> getIdListFiles() {
        return em.createQuery(
                        """
                                select new fileloader.loaderapp.model.dto.FileInfoProjection(fe.id, fe.fileName)
                                   from FileEntity fe
                                """,
                        FileInfoProjection.class)
                .getResultList();
    }

}
