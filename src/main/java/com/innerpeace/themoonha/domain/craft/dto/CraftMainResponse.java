package com.innerpeace.themoonha.domain.craft.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 문화공방 메인 페이지 응답 DTO
 *
 * @author 손승완
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	손승완       최초 생성
 * </pre>
 * @since 2024.08.25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CraftMainResponse {
    private List<PrologueDTO> prologueList;
    private List<WishLessonDTO> firstWishLessonList;
    private List<WishLessonDTO> secondWishLessonList;
    private List<SuggestionDTO> suggestionList;


    public static CraftMainResponse of(List<PrologueDTO> prologueList,
                                       List<WishLessonDTO> wishLessonList,
                                       List<SuggestionDTO> suggestionList) {

        String theme = wishLessonList.get(0).getTheme();
        Map<Boolean, List<WishLessonDTO>> wishLessonMap = wishLessonList.stream()
                .collect(Collectors.partitioningBy(wishLessonDTO -> wishLessonDTO.getTheme().equals(theme)));

        return CraftMainResponse.builder()
                .prologueList(prologueList)
                .firstWishLessonList(wishLessonMap.get(true))
                .secondWishLessonList(wishLessonMap.get(false))
                .suggestionList(suggestionList)
                .build();
    }
}
