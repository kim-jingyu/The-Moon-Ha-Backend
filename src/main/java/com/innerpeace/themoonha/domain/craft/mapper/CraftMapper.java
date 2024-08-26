package com.innerpeace.themoonha.domain.craft.mapper;

import com.innerpeace.themoonha.domain.craft.dto.PrologueDTO;
import com.innerpeace.themoonha.domain.craft.dto.PrologueDetailResponse;
import com.innerpeace.themoonha.domain.craft.dto.SuggestionDTO;
import com.innerpeace.themoonha.domain.craft.dto.WishLessonDTO;
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
 * </pre>
 */
public interface CraftMapper {
    List<PrologueDTO> selectPrologueList();
    List<WishLessonDTO> selectWishLessonList();

    List<SuggestionDTO> selectSuggestionList(Criteria criteria);

    PrologueDetailResponse selectPrologueDetail(@Param("prologueId") Long prologueId,
                                                @Param("memberId") Long memberId);

}
