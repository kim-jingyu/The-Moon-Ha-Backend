package com.innerpeace.themoonha.domain.craft.dto;

import lombok.*;

/**
 * 문화공방 제안합니다 댓글 작성 요청 DTO
 * @author 손승완
 * @since 2024.08.27
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  	손승완       최초 생성
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuggestionRequest {
    private String content;
}
