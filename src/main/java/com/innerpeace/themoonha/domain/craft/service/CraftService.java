package com.innerpeace.themoonha.domain.craft.service;

import com.innerpeace.themoonha.domain.craft.dto.CraftMainResponse;
import com.innerpeace.themoonha.domain.craft.dto.SuggestionRequest;
import com.innerpeace.themoonha.domain.craft.dto.SuggestionResponse;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.util.Criteria;

/**
 * 문화공방 서비스 인터페이스
 * @author 손승완
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	손승완       최초 생성
 * 2024.08.26   손승완       프롤로그 상세 조회 구현
 * 2024.08.27   손승완       제안합니다 댓글 작성 기능 추가
 * </pre>
 */
public interface CraftService {
    CraftMainResponse findCraftMain(Criteria criteria, Long memberId);

    CommonResponse addSuggestion(SuggestionRequest suggestionRequest, Long memberId);

    CommonResponse addPrologueLike(Long prologueId, Long memberId);

    CommonResponse addWishLessonVote(Long wishLessonId, Long memberId);

    SuggestionResponse findSuggestionList(Criteria criteria);
}
