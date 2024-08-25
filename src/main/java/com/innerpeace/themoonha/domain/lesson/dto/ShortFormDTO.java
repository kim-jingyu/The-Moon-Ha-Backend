package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

/**
 * 숏폼 목록 조회 시 숏폼 내용 DTO
 * @author 손승완
 * @since 2024.08.24
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.24  	손승완       최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortFormDTO {
    private Long shortFormId;
    private String name;
    private String thumbnailUrl;
    private String target;
}
