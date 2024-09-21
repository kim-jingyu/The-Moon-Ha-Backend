package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

/**
 * 숏폼 조회수 DTO
 *
 * @author 손승완
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.11  	손승완       최초 생성
 * </pre>
 * @since 2024.09.11
 */
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
