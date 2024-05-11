package ilya.coursework.fileloader.controller.rest;

import ilya.coursework.fileloader.model.dto.FileInfoProjection;
import ilya.coursework.fileloader.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
    private final FileService fileServiceImpl;

    @PostMapping
    @ResponseBody
    public UUID upload(@RequestBody MultipartFile file) {
        return fileServiceImpl.save(file);
    }

    @GetMapping("/{id}")
    public HttpEntity<ByteArrayResource> download(@PathVariable UUID id) {
        val fileEntity = fileServiceImpl.getById(id);
        val resource = new ByteArrayResource(fileEntity.getFile());
        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("file", fileEntity.getFileName());
        headers.setContentLength(fileEntity.getFile().length);
        return new HttpEntity<>(resource, headers);
    }

    @GetMapping
    public List<FileInfoProjection> getAllFileNames() {
        return fileServiceImpl.getIdListFiles();
    }
}

