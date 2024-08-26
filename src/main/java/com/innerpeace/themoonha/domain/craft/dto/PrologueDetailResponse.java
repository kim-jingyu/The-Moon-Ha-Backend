package com.innerpeace.themoonha.domain.craft.dto;

import lombok.*;

/**
 * 문화공방 프롤로그 상세 조회 응답 DTO
 * @author 손승완
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	손승완       최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class PrologueDetailResponse {
    private Long prologueId;
    private String title;
    private String videoUrl;
    private boolean alreadyLiked;
}
