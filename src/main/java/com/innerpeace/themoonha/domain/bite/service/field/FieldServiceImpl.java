package com.innerpeace.themoonha.domain.bite.service.field;

import com.innerpeace.themoonha.domain.bite.dto.field.*;
import com.innerpeace.themoonha.domain.bite.mapper.FieldMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.innerpeace.themoonha.global.exception.ErrorCode.*;

/**
 * 분야별 한 입 서비스 구현체
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  김진규        최초 생성
 * 2024.08.31  김진규        getFieldList, makeField, findFieldByTitle, findFieldByHashTags, getFieldContent 메서드 구현
 * </pre>
 * @since 2024.08.31
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    private static final String FIELD_CONTENT_IMAGE_PATH = "bite/field/content/image";
    private static final String FIELD_CONTENT_VIDEO_PATH = "bite/field/content/video";
    private static final String FIELD_THUMBNAIL_PATH = "bite/field/thumbnail";

    private final FieldMapper fieldMapper;
    private final S3Service s3Service;

    /**
     * 분야별 한 입 전체 콘텐츠 목록 조회 (최신순)
     * @return 분야별 한 입 콘텐츠 목록
     */
    @Override
    public List<FieldListResponse> getFieldList() {
        return fieldMapper.findFieldList();
    }

    /**
     * 분야별 한 입 전체 콘텐츠 목록 조회 (제목순)
     * @return 분야별 한 입 콘텐츠 목록
     */
    @Override
    public List<FieldListResponse> getFieldListOrderByTitle() {
        return fieldMapper.findFieldListOrderByTitle();
    }

    /**
     * 분야별 한 입 콘텐츠 상세 조회
     * @param fieldId
     * @return 분야별 한 입 콘텐츠
     */
    @Override
    public FieldDetailResponse getFieldContent(Long fieldId) {
        return fieldMapper.findFieldDetail(fieldId)
                .orElseThrow(() -> new CustomException(FIELD_NOT_FOUND));
    }

    @Override
    public List<FieldDetailResponse> getFieldContents() {
        return fieldMapper.findFieldDetails();
    }

    /**
     * 분야별 한 입 콘텐츠 등록
     *
     * @param memberId
     * @param fieldRequest
     * @param thumbnail
     * @param content
     * @return 등록된 분야별 한 입 콘텐츠 아이디
     */
    @Override
    public CommonResponse makeField(Long memberId, FieldRequest fieldRequest, MultipartFile thumbnail, MultipartFile content) {
        String contentPath = determineContentPath(content.getContentType());
        try {
            FieldDTO fieldDTO = createFieldDTO(memberId, fieldRequest, thumbnail, content, contentPath);
            saveFieldContent(fieldRequest, fieldDTO);
            return CommonResponse.from(String.valueOf(fieldDTO.getFieldId()));
        } catch (IOException e) {
            deleteS3Files(thumbnail.getOriginalFilename(), content.getOriginalFilename(), contentPath);
            throw new CustomException(S3_UPLOAD_FAILED);
        } catch (Exception e) {
            throw new CustomException(FIELD_CREATION_FAILED);
        }
    }

    /**
     * 분야별 한 입 타이틀 기반 콘텐츠 검색
     * @param keyword
     * @return 타이틀 기반으로 검색된 분야별 한 입 콘텐츠 목록
     */
    @Override
    public List<FieldSearchResponse> findFieldByTitle(String keyword) {
        return fieldMapper.findFieldListByTitle(keyword);
    }

    /**
     * 분야별 한 입 해시태그 기반 콘텐츠 검색
     * @param hashtags
     * @return 해시태그 기반으로 검색된 분야별 한 입 콘텐츠 목록
     */
    @Override
    @Cacheable(value = "fieldCache", key = "#hashtags")
    public List<FieldSearchResponse> findFieldByHashTags(List<String> hashtags) {
        return fieldMapper.findFieldListByHashtags(hashtags);
    }

    private String determineContentPath(String contentType) {
        if (checkImageFileType(contentType)) {
            return FIELD_CONTENT_IMAGE_PATH;
        } else if (checkVideoFileType(contentType)) {
            return FIELD_CONTENT_VIDEO_PATH;
        }
        throw new CustomException(UNSUPPORTED_CONTENT_TYPE);
    }

    private boolean checkImageFileType(String contentType) {
        return contentType != null && contentType.startsWith("image/");
    }

    private boolean checkVideoFileType(String contentType) {
        return contentType != null && contentType.startsWith("video/");
    }

    private void deleteS3Files(String thumbnailFilename, String contentFilename, String contentPath) {
        s3Service.deleteFile(FIELD_THUMBNAIL_PATH, thumbnailFilename);
        s3Service.deleteFile(contentPath, contentFilename);
    }

    private void saveFieldContent(FieldRequest fieldRequest, FieldDTO fieldDTO) {
        fieldMapper.insertField(fieldDTO);
        List<String> hashtags = fieldRequest.getHashtags();
        if (!hashtags.isEmpty()) {
            fieldMapper.insertHashtagAndFieldHashtag(hashtags, fieldDTO.getFieldId());
        }
    }

    private FieldDTO createFieldDTO(Long memberId, FieldRequest fieldRequest, MultipartFile thumbnail, MultipartFile content, String contentPath) throws IOException {
        return FieldDTO.of(
                memberId,
                fieldRequest,
                s3Service.saveFile(thumbnail, FIELD_THUMBNAIL_PATH),
                s3Service.saveFile(content, contentPath)
        );
    }
}
