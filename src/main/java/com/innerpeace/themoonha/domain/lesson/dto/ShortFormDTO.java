package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortFormDTO {
    private Long shortFormId;
    private String name;
    private String thumbnailUrl;
    private String target;
}
