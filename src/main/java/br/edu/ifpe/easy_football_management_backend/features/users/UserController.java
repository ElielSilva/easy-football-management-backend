package br.edu.ifpe.easy_football_management_backend.features.users;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.users.command.UserCommandHandler;
import br.edu.ifpe.easy_football_management_backend.features.users.query.UserQueryHandler;
import br.edu.ifpe.easy_football_management_backend.infrestructure.service.FileStorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserCommandHandler userCommandHandler;
    private final UserQueryHandler userQueryHandler;
    private final FileStorageService fileStorageService;

    public UserController(
            UserCommandHandler userCommandHandler,
            UserQueryHandler userQueryHandler, FileStorageService fileStorageService) {
        this.userCommandHandler = userCommandHandler;
        this.userQueryHandler = userQueryHandler;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("")
    public ResponseEntity<UserDTO> get(@RequestHeader("Authorization") String authHeader) {
        UserDTO user = userQueryHandler.get(authHeader);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User> > getAll() {
        List<User> users = userQueryHandler.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(@RequestParam("file") MultipartFile file) {
        String up = null;
        if (!file.isEmpty())
            up = fileStorageService.uploadFile(file);
        var res = fileStorageService.getFileName(up);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteUser (@RequestHeader("Authorization") String authHeader) {
        userCommandHandler.deleteteUsers(authHeader);
        return ResponseEntity.ok("sucesso");
    }
}
