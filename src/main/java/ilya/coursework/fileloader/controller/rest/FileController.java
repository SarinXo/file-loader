package ilya.coursework.fileloader.controller.rest;

import ilya.coursework.fileloader.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public UUID upload(@RequestParam("file") MultipartFile file) {
        return fileServiceImpl.save(file);
    }

    @GetMapping
    public MultipartFile download(UUID id) {
        return fileServiceImpl.getFileById(id);
    }

    @GetMapping
    public List<UUID> getAllFileNames() {
        return fileServiceImpl.getIdListFiles();
    }
}

