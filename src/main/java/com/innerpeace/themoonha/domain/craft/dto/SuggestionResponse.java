package com.innerpeace.themoonha.domain.craft.dto;

import com.innerpeace.themoonha.global.dto.PageDTO;
import lombok.*;

import java.util.List;

/**
 * 문화공방 제안합니다 댓글 페이징 응답 DTO
 *
 * @author 손승완
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	손승완       최초 생성
 * </pre>
 * @since 2024.09.04
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SuggestionResponse {
    private PageDTO pageDTO;
    private List<SuggestionDTO> suggestionList;

    public static SuggestionResponse of(PageDTO pageDTO, List<SuggestionDTO> suggestionList) {
        return SuggestionResponse.builder()
                .pageDTO(pageDTO)
                .suggestionList(suggestionList)
                .build();
    }
}
