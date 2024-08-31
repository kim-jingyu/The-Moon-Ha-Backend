package com.innerpeace.themoonha.domain.bite.dto.field;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 비포애프터 상세보기용 응답 DTO
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
public class FieldResponseForDetail {
    private String contentUrl;
    private int contentIsImage;
    private String title;
    private String profileImageUrl;
    private String memberName;
    private List<String> hashtags;
}
