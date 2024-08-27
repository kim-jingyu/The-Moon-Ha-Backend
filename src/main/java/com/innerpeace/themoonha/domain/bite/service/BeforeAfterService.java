package com.innerpeace.themoonha.domain.bite.service;

import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterRequest;
import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterResponse;
import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterSearchResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
 * </pre>
 * @since 2024.08.27
 */
public interface BeforeAfterService {
    List<BeforeAfterResponse> getBeforeAfterList();
    Long makeBeforeAfter(Long memberId, BeforeAfterRequest beforeAfterRequest, MultipartFile beforeContent, MultipartFile afterContent) throws IOException;
    List<BeforeAfterSearchResponse> findBeforeAfterByTitle(String keyword);
    List<BeforeAfterSearchResponse> findBeforeAfterByHashTags(List<String> hashtags);
}
