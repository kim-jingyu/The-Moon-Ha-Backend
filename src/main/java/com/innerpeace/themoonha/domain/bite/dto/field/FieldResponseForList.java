package com.innerpeace.themoonha.domain.bite.dto.field;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 비포애프터 리스트용 응답 DTO
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27   김진규      최초 생성
 * 2024.08.31   김진규      변수 수정
 * </pre>
 * @since 2024.08.27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FieldResponseForList {
    private String thumbnailUrl;
    private String title;
    private String profileImageUrl;
    private String memberName;
}
