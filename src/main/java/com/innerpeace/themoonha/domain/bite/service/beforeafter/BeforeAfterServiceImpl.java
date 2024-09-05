package com.innerpeace.themoonha.domain.bite.service.beforeafter;

import com.innerpeace.themoonha.domain.bite.dto.beforeafter.*;
import com.innerpeace.themoonha.domain.bite.mapper.BeforeAfterMapper;
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
 * 비포애프터 서비스 구현체
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  김진규        최초 생성
 * 2024.08.27  김진규        getBeforeAfterList 메서드 구현
 * 2024.08.28  김진규        makeBeforeAfter, findBeforeAfterByTitle, findBeforeAfterByHashTags 메서드 구현
 * 2024.08.30  김진규        makeBeforeAfter 메서드 수정
 * 2024.08.31  김진규        getBeforeAfterContent 메서드 구현
 * </pre>
 * @since 2024.08.27
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeforeAfterServiceImpl implements BeforeAfterService {
    private static final String BEFORE_THUMBNAIL_PATH = "bite/before-after/before/thumbnail";
    private static final String AFTER_THUMBNAIL_PATH = "bite/before-after/after/thumbnail";
    private static final String BEFORE = "before";
    private static final String AFTER = "after";

    private final BeforeAfterMapper beforeAfterMapper;
    private final S3Service s3Service;

    /**
     * 비포애프터 전체 콘텐츠 목록 조회 (최신순)
     * @return 비포애프터 콘텐츠 목록
     */
    @Override
    public List<BeforeAfterListResponse> getBeforeAfterList() {
        return beforeAfterMapper.findBeforeAfterList();
    }

    /**
     * 비포애프터 전체 콘텐츠 목록 조회 (제목순)
     * @return 비포애프터 콘텐츠 목록
     */
    @Override
    public List<BeforeAfterListResponse> getBeforeAfterListOrderByTitle() {
        return beforeAfterMapper.findBeforeAfterListOrderByTitle();
    }

    /**
     * 비포애프터 콘텐츠 상세 조회
     * @param beforeAfterId
     * @return 비포애프터 콘텐츠
     */
    @Override
    public BeforeAfterDetailResponse getBeforeAfterContent(Long beforeAfterId) {
        return beforeAfterMapper.findBeforeAfterDetail(beforeAfterId)
                .orElseThrow(() -> new CustomException(BEFORE_AFTER_NOT_FOUND));
    }

    /**
     * 비포애프터 콘텐츠 등록
     *
     * @param memberId
     * @param beforeAfterRequest
     * @param beforeThumbnail
     * @param afterThumbnail
     * @param beforeContent
     * @param afterContent
     * @return 등록된 비포애프터 콘텐츠 아이디
     * @throws IOException
     */
    @Override
    @Transactional
    public CommonResponse makeBeforeAfter(Long memberId, BeforeAfterRequest beforeAfterRequest, MultipartFile beforeThumbnail, MultipartFile afterThumbnail, MultipartFile beforeContent, MultipartFile afterContent) {
        String beforeContentPath = determineContentPath(beforeContent.getContentType(), BEFORE);
        String afterContentPath = determineContentPath(beforeContent.getContentType(), AFTER);
        try {
            BeforeAfterDTO beforeAfterDTO = createBeforeAfterDTO(memberId, beforeAfterRequest, beforeThumbnail, afterThumbnail, beforeContent, afterContent, beforeContentPath, afterContentPath);
            saveBeforeAfterContent(beforeAfterRequest, beforeAfterDTO);
            return CommonResponse.from(String.valueOf(beforeAfterDTO.getBeforeAfterId()));
        } catch (IOException e) {
            throw new CustomException(S3_UPLOAD_FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            deleteS3Files(beforeThumbnail.getOriginalFilename(), afterThumbnail.getOriginalFilename(), beforeContent.getOriginalFilename(), afterContent.getOriginalFilename(), beforeContentPath, afterContentPath);
            throw new CustomException(BEFORE_AFTER_CREATION_FAILED);
        }
    }

    /**
     * 비포애프터 타이틀 기반 콘텐츠 검색
     * @param keyword
     * @return 타이틀 기반으로 검색된 비포애프터 콘텐츠 목록
     */
    @Override
    public List<BeforeAfterSearchResponse> findBeforeAfterByTitle(String keyword) {
        return beforeAfterMapper.findBeforeAfterListByTitle(keyword);
    }

    /**
     * 비포애프터 해시태그 기반 콘텐츠 검색
     * @param hashtags
     * @return 해시태그 기반으로 검색된 비포애프터 콘텐츠 목록
     */
    @Override
    @Cacheable(value = "beforeAfterCache", key = "#hashtags")
    public List<BeforeAfterSearchResponse> findBeforeAfterByHashTags(List<String> hashtags) {
        return beforeAfterMapper.findBeforeAfterListByHashtags(hashtags);
    }

    private String determineContentPath(String contentType, String prefix) {
        if (checkImageFileType(contentType)) {
            return "bite/before-after/" + prefix + "/content/image";
        } else if (checkVideoFileType(contentType)) {
            return "bite/before-after/" + prefix + "/content/video";
        }
        throw new CustomException(UNSUPPORTED_CONTENT_TYPE);
    }

    private boolean checkImageFileType(String contentType) {
        return contentType != null && contentType.startsWith("image/");
    }

    private boolean checkVideoFileType(String contentType) {
        return contentType != null && contentType.startsWith("video/");
    }

    private void saveBeforeAfterContent(BeforeAfterRequest beforeAfterRequest, BeforeAfterDTO beforeAfterDTO) {
        beforeAfterMapper.insertBeforeAfter(beforeAfterDTO);
        List<String> hashtags = beforeAfterRequest.getHashtags();
        if (!hashtags.isEmpty()) {
            beforeAfterMapper.insertHashtagAndBeforeAfterHashtag(hashtags, beforeAfterDTO.getBeforeAfterId());
        }
    }

    private void deleteS3Files(String beforeThumbnailFilename, String afterThumbnailFilename, String beforeContentFilename, String afterContentFilename, String beforeContentPath, String afterContentPath) {
        s3Service.deleteFile(BEFORE_THUMBNAIL_PATH, beforeThumbnailFilename);
        s3Service.deleteFile(AFTER_THUMBNAIL_PATH, afterThumbnailFilename);
        s3Service.deleteFile(beforeContentPath, beforeContentPath);
        s3Service.deleteFile(afterContentPath, afterContentPath);
    }

    private BeforeAfterDTO createBeforeAfterDTO(Long memberId, BeforeAfterRequest beforeAfterRequest, MultipartFile beforeThumbnail, MultipartFile afterThumbnail, MultipartFile beforeContent, MultipartFile afterContent, String beforeContentPath, String afterContentPath) throws IOException {
        return BeforeAfterDTO.of(
                memberId,
                beforeAfterRequest,
                s3Service.saveFile(beforeThumbnail, BEFORE_THUMBNAIL_PATH),
                s3Service.saveFile(afterThumbnail, AFTER_THUMBNAIL_PATH),
                s3Service.saveFile(beforeContent, beforeContentPath),
                s3Service.saveFile(afterContent, afterContentPath)
        );
    }
}
