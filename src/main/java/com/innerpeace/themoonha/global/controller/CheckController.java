package com.innerpeace.themoonha.global.controller;

import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.dto.S3PreSignedUrlRequest;
import com.innerpeace.themoonha.global.dto.S3UploadCompleteDTO;
import com.innerpeace.themoonha.global.dto.S3UploadSignedUrlRequest;
import com.innerpeace.themoonha.global.service.S3Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

//    @GetMapping("/s3/preSignedUrl")
//    public ResponseEntity<String> getPreSignedUrl(S3PreSignedUrlRequest s3PreSignedUrlRequest){
//        log.info("/api/s3/preSignedUrl : {}", s3PreSignedUrlRequest.getFileName());
//        String preSignedUrl = s3Service.getPreSignedUrl(s3PreSignedUrlRequest.getFileName());
//        return ResponseEntity.ok(preSignedUrl);
//    }

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

    @PostMapping("/s3/createUpload")
    public ResponseEntity<String> createS3Upload(@RequestBody S3PreSignedUrlRequest s3CreateUpload) {
        log.info("/s3/createUpload : {} ", s3CreateUpload.toString());
        return ResponseEntity.ok(s3Service.multipartUploadWithS3Client(s3CreateUpload.getFileName()));
    }

    @GetMapping("/s3/uploadSignedUrl")
    public ResponseEntity<String> uploadSignedUrl(@RequestBody S3UploadSignedUrlRequest s3UploadSignedUrlRequest){
        log.info("/s3/uploadSignedUrl : {} ", s3UploadSignedUrlRequest.toString());
        return ResponseEntity.ok(s3Service.getUploadSignedUrl(s3UploadSignedUrlRequest));
    }
    @GetMapping("/s3/completeUpload")
    public ResponseEntity<CommonResponse> completeUpload(@RequestBody S3UploadCompleteDTO s3UploadCompleteDTO){
        log.info("/s3/completeUpload : {} ", s3UploadCompleteDTO.toString());
       s3Service.completeUpload(s3UploadCompleteDTO);
        return ResponseEntity.ok(CommonResponse.from("성공하였습니다."));
    }

}
