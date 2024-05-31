package fileloader.loaderapp.controller;

import fileloader.loaderapp.model.dto.FileInfoProjection;
import fileloader.loaderapp.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
    private final FileService fileServiceImpl;

    @PostMapping
    public UUID upload(@RequestBody MultipartFile file) {
        return fileServiceImpl.save(file);
    }

    @GetMapping
    public List<FileInfoProjection> getAllFileNames() {
        return fileServiceImpl.getIdListFiles();
    }
}

