package com.innerpeace.themoonha.domain.bite.dto;

import lombok.*;

import java.util.List;

/**
 * 비포애프터 응답 DTO
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
public class BeforeAfterResponse {
    private String beforeUrl;
    private int beforeIsImage;
    private String afterUrl;
    private int afterIsImage;
    private String title;
    private String profileImageUrl;
    private String memberName;
    private List<String> hashtags;
}
