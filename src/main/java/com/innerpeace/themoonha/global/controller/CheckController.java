package com.innerpeace.themoonha.global.controller;

import com.innerpeace.themoonha.global.dto.S3PreSignedUrlRequest;
import com.innerpeace.themoonha.global.service.S3Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CheckController {
    private final S3Service s3Service;

    @GetMapping("/health-check")
    public ResponseEntity<Void> checkHealthStatus() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/s3/preSignedUrl")
    public ResponseEntity<String> getPreSignedUrl(S3PreSignedUrlRequest s3PreSignedUrlRequest){
        log.info("/api/s3/preSignedUrl : {}", s3PreSignedUrlRequest.getFileName());
        String preSignedUrl = s3Service.getPreSignedUrl(s3PreSignedUrlRequest.getFileName());
        return ResponseEntity.ok(preSignedUrl);
    }

    @GetMapping("/s3/preSignedUrl/list")
    public ResponseEntity<Map<String,String>> getPreSignedUrl(@RequestParam List<String> fileNames){
        log.info("/api/s3/preSignedUrl : {}", fileNames);
        Map<String, String> preSignedUrls = new HashMap<>();

        for (String fileName : fileNames) {
            String preSignedUrl = s3Service.getPreSignedUrl(fileName);
            preSignedUrls.put(fileName, preSignedUrl);
        }

        return ResponseEntity.ok(preSignedUrls);
    }
}
