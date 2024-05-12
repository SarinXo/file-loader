package ilya.coursework.fileloader.controller.rest;

import ilya.coursework.fileloader.model.dto.FileInfoProjection;
import ilya.coursework.fileloader.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable UUID id) {
        val fileEntity = fileServiceImpl.getById(id);
        val resource = new ByteArrayResource(fileEntity.getFile());
        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        val contentDisposition = ContentDisposition.builder("attachment")
                .filename(fileEntity.getFileName(), StandardCharsets.UTF_8)
                .build();

        headers.setContentDisposition(contentDisposition);
        headers.setContentLength(fileEntity.getFile().length);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping
    public List<FileInfoProjection> getAllFileNames() {
        return fileServiceImpl.getIdListFiles();
    }
}

