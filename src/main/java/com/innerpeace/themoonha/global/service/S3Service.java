package com.innerpeace.themoonha.global.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
 * </pre>
 * @since 2024.08.27
 */
@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 s3Client;

    @Value("${aws.bucket}")
    private String bucket;

    public String saveFile(MultipartFile multipartFile, String folderName) throws IOException {
        String originalFilename = getFileNameServer(multipartFile);
        String fullPath = getFullPath(folderName, originalFilename);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        s3Client.putObject(bucket, fullPath, multipartFile.getInputStream(), metadata);
        return s3Client.getUrl(bucket, fullPath).toString();
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
        return new UrlResource(s3Client.getUrl(bucket, getFullPath(folderName, originalFilename)));
    }

    public void deleteFile(String folderName, String originalFilename) {
        s3Client.deleteObject(bucket, getFullPath(folderName, originalFilename));
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
