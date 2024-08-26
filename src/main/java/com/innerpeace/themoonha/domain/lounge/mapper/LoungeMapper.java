package com.innerpeace.themoonha.domain.lounge.mapper;

import com.innerpeace.themoonha.domain.lounge.dto.LoungeListResponse;

import java.util.List;
import java.util.Optional;

/**
 * 라운지 매퍼
 * @author 조희정
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	조희정       최초 생성
 * 2024.08.25  	조희정       selectLoungeList 메서드 추가
 * </pre>
 */
public interface LoungeMapper {
    /**
     * 라운지 목록 조회
     * @param memberId
     * @return
     */
    List<LoungeListResponse> selectLoungeList(Long memberId);
}
