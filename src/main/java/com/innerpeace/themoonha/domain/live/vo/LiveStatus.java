package com.innerpeace.themoonha.domain.live.vo;

import lombok.Getter;

/**
 * LiveStatus
 * @author 김진규
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	김진규       최초 생성
 * </pre>
 */
@Getter
public enum LiveStatus {
    ON_AIR,
    ENDED,
    UPCOMING
}
