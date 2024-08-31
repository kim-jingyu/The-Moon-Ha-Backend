package com.innerpeace.themoonha.domain.bite.dto.field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

/**
 * 분야별 한 입 해시태그 DTO
 * @author 김진규
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.28   김진규      최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class FieldHashtag {
    private Long fieldHashtagId;
    private Long fieldId;
    private Long hashtagId;
}
