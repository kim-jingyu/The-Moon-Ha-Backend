package com.innerpeace.themoonha.domain.bite.dto.beforeafter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 비포애프터 검색용 응답 DTO
 * @author 김진규
 * @since 2024.08.27
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27   김진규      최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeforeAfterSearchResponse {
    private Long beforeAfterId;
    private String title;
}
