package com.innerpeace.themoonha.domain.craft.dto;

import com.innerpeace.themoonha.global.dto.PageDTO;
import lombok.*;

import java.util.List;

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
