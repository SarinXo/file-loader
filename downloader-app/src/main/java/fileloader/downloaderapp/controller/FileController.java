package fileloader.downloaderapp.controller;


import fileloader.downloaderapp.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static fileloader.downloaderapp.util.HeadersUtil.forDownload;

@Service
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
    private final FileService fileServiceImpl;

    @Cacheable("fileCache")
    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable UUID id) {
        val fileEntity = fileServiceImpl.getById(id);
        val file = new ByteArrayResource(fileEntity.getFile());
        val headers = forDownload(fileEntity.getFileName(), fileEntity.getFile().length);
        return ResponseEntity.ok()
                .headers(headers)
                .body(file);
    }
}

