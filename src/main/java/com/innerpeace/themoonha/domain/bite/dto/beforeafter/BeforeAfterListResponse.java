package com.innerpeace.themoonha.domain.bite.dto.beforeafter;

import lombok.*;

/**
 * 비포애프터 리스트용 응답 DTO
 * @author 김진규
 * @since 2024.08.27
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
public class BeforeAfterListResponse {
    private Long beforeAfterId;
    private String beforeThumbnailUrl;
    private String afterThumbnailUrl;
    private String title;
    private String profileImgUrl;
    private String memberName;
}
