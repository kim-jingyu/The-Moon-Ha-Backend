package com.innerpeace.themoonha.domain.craft.mapper;

import com.innerpeace.themoonha.domain.craft.dto.*;
import com.innerpeace.themoonha.global.util.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 문화공방 매퍼
 * @author 손승완
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	손승완       최초 생성
 * 2024.08.26  	손승완       프롤로그 상세 조회 메서드 추가
 * 2024.08.27  	손승완       제안합니다 댓글 작성 메서드 추가
 * </pre>
 */
public interface CraftMapper {
    List<PrologueDTO> selectPrologueList(Long memberId);
    List<WishLessonDTO> selectWishLessonList(Long memberId);

    List<SuggestionDTO> selectSuggestionList(Criteria criteria);

    int insertSuggestion(@Param("suggestionRequest") SuggestionRequest suggestionRequest,
                         @Param("memberId") Long memberId);

    int insertPrologueLike(@Param("prologueId") Long prologueId,
                           @Param("memberId") Long memberId);

    int insertWishLessonVote(@Param("wishLessonId") Long wishLessonId,
                             @Param("memberId") Long memberId);
}
