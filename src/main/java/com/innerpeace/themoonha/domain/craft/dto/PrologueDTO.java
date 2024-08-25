package com.innerpeace.themoonha.domain.craft.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PrologueDTO {
    private Long prologueId;
    private String title;
    private String thumbnailUrl;
    private int likeCnt;
}
