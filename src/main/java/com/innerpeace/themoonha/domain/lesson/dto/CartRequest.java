package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

/**
 * 장바구니 담기 요청 DTO
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
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartRequest {
    private Long lessonId;
    private Long memberId;
    private boolean onlineYn;
}
