package com.innerpeace.themoonha.global.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@PropertySource(value={"classpath:application.properties"})
public class S3Service {
    private final AmazonS3 s3Client;

    @Value("${aws.bucket}")
    private String bucket;

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        s3Client.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return s3Client.getUrl(bucket, originalFilename).toString();
    }

    public UrlResource downloadFile(String originalFilename) {
        return new UrlResource(s3Client.getUrl(bucket, originalFilename));
    }

    public void deleteFile(String originalFilename) {
        s3Client.deleteObject(bucket, originalFilename);
    }
}
