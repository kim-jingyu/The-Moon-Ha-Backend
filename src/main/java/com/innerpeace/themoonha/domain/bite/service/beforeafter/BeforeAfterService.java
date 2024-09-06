package com.innerpeace.themoonha.domain.bite.service.beforeafter;

import com.innerpeace.themoonha.domain.bite.dto.beforeafter.BeforeAfterRequest;
import com.innerpeace.themoonha.domain.bite.dto.beforeafter.BeforeAfterDetailResponse;
import com.innerpeace.themoonha.domain.bite.dto.beforeafter.BeforeAfterListResponse;
import com.innerpeace.themoonha.domain.bite.dto.beforeafter.BeforeAfterSearchResponse;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 비포애프터 서비스 인터페이스
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  김진규        최초 생성
 * 2024.08.27  김진규        getBeforeAfterList 메서드 추가
 * 2024.08.28  김진규        makeBeforeAfter, findBeforeAfterByTitle, findBeforeAfterByHashTags 메서드 추가
 * </pre>
 * @since 2024.08.27
 */
public interface BeforeAfterService {
    List<BeforeAfterListResponse> getBeforeAfterList();
    BeforeAfterDetailResponse getBeforeAfterContent(Long beforeAfterId);
    CommonResponse makeBeforeAfter(Long memberId, BeforeAfterRequest beforeAfterRequest, MultipartFile beforeThumbnail, MultipartFile afterThumbnail, MultipartFile beforeContent, MultipartFile afterContent);
    List<BeforeAfterSearchResponse> findBeforeAfterByTitle(String keyword);
    List<BeforeAfterSearchResponse> findBeforeAfterByHashTags(List<String> hashtags);
    List<BeforeAfterListResponse> getBeforeAfterListOrderByTitle();
}
