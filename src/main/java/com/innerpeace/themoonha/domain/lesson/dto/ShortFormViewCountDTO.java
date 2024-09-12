package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ShortFormViewCountDTO {
    private Long shortFormId;
    private Integer viewCount;
}
