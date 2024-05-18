package ilya.coursework.fileloader.service.impl;

import ilya.coursework.fileloader.model.dto.FileInfoProjection;
import ilya.coursework.fileloader.model.entity.FileEntity;
import ilya.coursework.fileloader.repository.FileRepository;
import ilya.coursework.fileloader.service.FileService;
import ilya.coursework.fileloader.util.ZipUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @PersistenceContext
    private final EntityManager em;
    private final FileRepository fileRepository;
    private final ZipUtil zipUtil;

    @Override
    public UUID save(MultipartFile file) {
        try {
            val zipArchive = zipUtil.zip(file.getBytes());
            val fileEntity = new FileEntity(zipArchive, file.getOriginalFilename());
            return fileRepository.save(fileEntity).getId();
        } catch (IOException e) {
            throw new RuntimeException("Cannot save file");
        }
    }

    @Override
    public FileEntity getById(UUID id) {
        val file = fileRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("File doesn't exists")
                );
        val zipArchive = file.getFile();
        val unzip = zipUtil.unzip(zipArchive);
        file.setFile(unzip);
        return file;
    }

    @Override
    public List<FileInfoProjection> getIdListFiles() {
        return em.createQuery(
                """
                        select new ilya.coursework.fileloader.model.dto.FileInfoProjection(fe.id, fe.fileName)
                           from FileEntity fe
                        """,
                        FileInfoProjection.class)
                .getResultList();
    }
}
