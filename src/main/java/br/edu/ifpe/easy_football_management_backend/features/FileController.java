package br.edu.ifpe.easy_football_management_backend.features;

import br.edu.ifpe.easy_football_management_backend.infrestructure.service.FileStorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
@SecurityRequirement(name = "bearerAuth")
public class FileController {
    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String up = null;
        if (!file.isEmpty())
            up = fileStorageService.uploadFile(file);
        var res = fileStorageService.getFileName(up);
        return ResponseEntity.ok(res);
    }
}
