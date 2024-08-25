package com.innerpeace.themoonha.domain.craft.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class WishLessonDTO {
    private Long wishLessonId;
    private String title;
    private int voteCnt;
    private String theme;
}
