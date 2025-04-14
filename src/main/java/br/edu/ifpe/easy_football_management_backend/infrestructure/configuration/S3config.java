package br.edu.ifpe.easy_football_management_backend.infrestructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;


@Configuration
public class S3config {

    @Value("${S3_ACCESS_KEY}")
    private String accessKey;

    @Value("${S3_SECRET_KEY}")
    private String secretKey;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    @Value("${S3_REGION}")
    private String s3region;

    @Bean
    public S3Client s3Client() {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                accessKey,
                secretKey
        );

        return S3Client.builder()
                .region(Region.of(s3region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }


    @Bean
    public String bucketName() {
        return this.bucketName;
    }
}
