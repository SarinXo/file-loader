package ilya.coursework.fileloader.service.impl;

import ilya.coursework.fileloader.repository.FileRepository;
import ilya.coursework.fileloader.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    public UUID save(MultipartFile file) {
        return null;
    }

    @Override
    public MultipartFile getFileById(UUID id) {
        val file = fileRepository.findFileById(id);
        return null;
    }

    @Override
    public List<UUID> getIdListFiles() {
        return null;
    }
}
