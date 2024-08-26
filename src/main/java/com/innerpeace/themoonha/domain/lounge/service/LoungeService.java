package com.innerpeace.themoonha.domain.lounge.service;

import com.innerpeace.themoonha.domain.lounge.dto.LoungeListResponse;

import java.util.List;

/**
 * 라운지 서비스 인터페이스
 * @author 조희정
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	조희정       최초 생성
 * 2024.08.25  	조희정       findLoungeList 메서드 추가
 * </pre>
 */
public interface LoungeService {
    /**
     * 라운지 목록 조회
     * @param memberId
     * @return
     */
    List<LoungeListResponse> findLoungeList(Long memberId);
}
