package br.edu.ifpe.easy_football_management_backend.infrestructure.service;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.UploadFileException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class FileStorageService {

    @Autowired
    private MinioClient minioClient;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    public String uploadFile(MultipartFile file) {
        try {
            var args = BucketExistsArgs.builder().bucket(bucketName).build();
            boolean found = minioClient.bucketExists(args);
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            try (InputStream inputStream = file.getInputStream()) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .stream(inputStream, file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build());
            } catch (Exception e) {
                throw new UploadFileException(e.getMessage());
            }
            return fileName;
        } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException |
                 InvalidResponseException | IOException | XmlParserException | InternalException | InvalidKeyException e) {
            throw new UploadFileException(e.getMessage());
        }
    }

    public String getFileName(String fileName) {
        return String.format("%s/%s/%s", "http://localhost:9000", bucketName, fileName);
    }
}
