package com.innerpeace.themoonha.domain.bite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

/**
 * 비포애프터 해시태그 DTO
 * @author 김진규
 * @since 2024.08.28
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
public class BeforeAfterHashtag {
    private Long beforeAfterHashtagId;
    private Long beforeAfterId;
    private Long hashtagId;
}
