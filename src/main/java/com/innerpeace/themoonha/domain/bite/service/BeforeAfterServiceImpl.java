package com.innerpeace.themoonha.domain.bite.service;

import com.innerpeace.themoonha.domain.bite.dto.*;
import com.innerpeace.themoonha.domain.bite.mapper.BeforeAfterMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

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
 * </pre>
 * @since 2024.08.27
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeforeAfterServiceImpl implements BeforeAfterService {
    private final BeforeAfterMapper beforeAfterMapper;
    private final S3Service s3Service;

    /**
     * 비포애프터 전체 콘텐츠 목록 조회
     * @return 비포애프터 콘텐츠 목록
     */
    @Override
    public List<BeforeAfterResponse> getBeforeAfterList() {
        return beforeAfterMapper.findBeforeAfterList();
    }

    /**
     * 비포애프터 콘텐츠 등록
     * @param memberId
     * @param beforeAfterRequest
     * @param beforeContent
     * @param afterContent
     * @return 등록된 비포애프터 콘텐츠 아이디
     * @throws IOException
     */
    @Override
    @Transactional
    public CommonResponse makeBeforeAfter(Long memberId, BeforeAfterRequest beforeAfterRequest, MultipartFile beforeContent, MultipartFile afterContent) throws IOException {
        BeforeAfterDTO beforeAfterDTO = BeforeAfterDTO.of(memberId, beforeAfterRequest, s3Service.saveFile(beforeContent, "before-after"), s3Service.saveFile(afterContent, "before-after"));
        beforeAfterMapper.insertBeforeAfter(beforeAfterDTO);
        for (String hashtag : beforeAfterRequest.getHashtags()) {
            Optional<Hashtag> optionalHashtag = beforeAfterMapper.findHashtag(hashtag);
            if (optionalHashtag.isPresent()) {
                beforeAfterMapper.insertBeforeAfterHashtag(beforeAfterDTO.getBeforeAfterId(), optionalHashtag.get().getHashtagId());
                continue;
            }
            Map<String, Object> params = new HashMap<>();
            params.put("name", hashtag);
            beforeAfterMapper.insertHashtag(params);
            beforeAfterMapper.insertBeforeAfterHashtag(beforeAfterDTO.getBeforeAfterId(), (Long) params.get("hashtagId"));
        }
        return CommonResponse.from(String.valueOf(beforeAfterDTO.getBeforeAfterId()));
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
}
