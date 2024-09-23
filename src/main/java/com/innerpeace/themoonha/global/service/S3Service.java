package com.innerpeace.themoonha.global.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.innerpeace.themoonha.global.dto.S3UploadCompleteDTO;
import com.innerpeace.themoonha.global.dto.S3UploadCompleteDTO.S3UploadPartsDetailDTO;
import com.innerpeace.themoonha.global.dto.S3UploadSignedUrlRequest;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.CompletedMultipartUpload;
import software.amazon.awssdk.services.s3.model.CompletedPart;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.UploadPartRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest;

/**
 * S3 클라이언트 서비스
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  김진규        최초 생성
 * 2024.09.17  최유경        Presigned Url 생성
 * </pre>
 * @since 2024.08.27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {
    private final AmazonS3 amazonS3;
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    static private final String key = UUID.randomUUID().toString();

    @Value("${aws.bucket}")
    private String bucket;
    @Value("${aws.cloudfront.url}")
    private String cloudFrontUrl;

    public String saveFile(MultipartFile multipartFile, String folderName) throws IOException {
        String originalFilename = getFileNameServer(multipartFile);
        String fullPath = getFullPath(folderName, originalFilename);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        amazonS3.putObject(bucket, fullPath, multipartFile.getInputStream(), metadata);

        return cloudFrontUrl + amazonS3.getUrl(bucket, fullPath).getPath();
    }

    public List<String> saveFiles(List<MultipartFile> files, String path) {
        List<String> images = new ArrayList<>();
        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
                try {
                    String savedFileUrl = saveFile(file, path);
                    images.add(savedFileUrl);
                } catch (IOException e) {
                    throw new CustomException(ErrorCode.LOUNGE_IMG_UPLOAD_FAILED);
                }
            }
        }
        return images;
    }

    public UrlResource downloadFile(String folderName, String originalFilename) {
        return new UrlResource(amazonS3.getUrl(bucket, getFullPath(folderName, originalFilename)));
    }

    public void deleteFile(String folderName, String originalFilename) {
        amazonS3.deleteObject(bucket, getFullPath(folderName, originalFilename));
    }



    public String multipartUploadWithS3Client(String filePath){
        CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
                .bucket(bucket)
                .key(filePath)
                .build();

        CreateMultipartUploadResponse createMultipartUploadResponse = s3Client.createMultipartUpload(createMultipartUploadRequest);
//        log.info("multipartUploadWithS3Client : {} " , createMultipartUploadResponse.uploadId());
        return createMultipartUploadResponse.uploadId();
    }

    public String getUploadSignedUrl(S3UploadSignedUrlRequest s3UploadSignedUrlRequest){
        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(bucket)
                .key(s3UploadSignedUrlRequest.getFullPath())
                .uploadId(s3UploadSignedUrlRequest.getUploadId())
                .partNumber(s3UploadSignedUrlRequest.getPartNumber())
                .build();

        UploadPartPresignRequest uploadPartPresignRequest = UploadPartPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .uploadPartRequest(uploadPartRequest)
                .build();

        PresignedUploadPartRequest presignedUploadPartRequest = s3Presigner.presignUploadPart(uploadPartPresignRequest);
//        log.info("getUploadSignedUrl : {} " , presignedUploadPartRequest.url().toString());
        return presignedUploadPartRequest.url().toString();
    }

    public void completeUpload(S3UploadCompleteDTO s3UploadCompleteDTO){
        List<CompletedPart> completedParts = new ArrayList<>();

        for(S3UploadPartsDetailDTO partForm : s3UploadCompleteDTO.getParts()) {
            CompletedPart part = CompletedPart.builder()
                    .partNumber(partForm.getPartNumber())
                    .eTag(partForm.getAwsETag())
                    .build();
            completedParts.add(part);
        }

        CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder()
                .parts(completedParts)
                .build();

        String fileName = s3UploadCompleteDTO.getFullPath();
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                CompleteMultipartUploadRequest.builder()
                        .bucket(bucket)
                        .key(fileName)
                        .uploadId(s3UploadCompleteDTO.getUploadId())
                        .multipartUpload(completedMultipartUpload)
                        .build();

        CompleteMultipartUploadResponse completeMultipartUploadResponse = s3Client.completeMultipartUpload(completeMultipartUploadRequest);

        // s3에 업로드된 파일 이름
        String objectKey = completeMultipartUploadResponse.key();

//        log.info("completeUpload : {}", objectKey);
    }


    public String getPreSignedUrl(String fileName){
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = Instant.now().toEpochMilli();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePreSignedUrlRequest(fileName);

        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        log.info("s3Client : {}", url.toString());
        return url.toString();
    }

    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String fileName) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket,  fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(getPreSignedUrlExpiration());

        return generatePresignedUrlRequest;
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 3;
        expiration.setTime(expTimeMillis);
        return expiration;
    }

    private String getFullPath(String folderName, String originalFilename) {
        return folderName + "/" + originalFilename;
    }

    /**
     * 파일 이름 설정
     * @param multipartFile
     * @return
     */
    private static String getFileNameServer(MultipartFile multipartFile) {
        // 날짜
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // uuid
        String uuid = UUID.randomUUID().toString();

        String originalName = multipartFile.getOriginalFilename();
        int pos = multipartFile.getOriginalFilename().lastIndexOf(".");

        // 원본 파일 명
        String baseName = originalName.substring(0, pos);
        // 파일 확장자
        String ext = multipartFile.getOriginalFilename().substring(pos + 1);

        return today + "_" + uuid.split("-")[0] + "_" + baseName + "." + ext;
    }
}
