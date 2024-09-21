package com.innerpeace.themoonha.domain.craft.dto;

import lombok.*;

/**
 * 문화공방 듣고 싶은 강좌 응답 DTO
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
public class WishLessonDTO {
    private Long wishLessonId;
    private String title;
    private int voteCnt;
    private String theme;
    private boolean alreadyVoted;
}
