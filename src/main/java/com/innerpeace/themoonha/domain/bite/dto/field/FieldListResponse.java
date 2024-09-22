package com.innerpeace.themoonha.domain.bite.dto.field;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 분야별 한 입 리스트용 응답 DTO
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31   김진규      최초 생성
 * </pre>
 * @since 2024.08.31
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FieldListResponse {
    private String lessonTitle;
    private Long categoryId;
    private String category;
    private Long fieldId;
    private String thumbnailUrl;
    private String title;
    private String profileImgUrl;
    private String memberName;
}
