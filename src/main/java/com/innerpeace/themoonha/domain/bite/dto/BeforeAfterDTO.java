package com.innerpeace.themoonha.domain.bite.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 비포애프터 DTO
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
@Builder
public class BeforeAfterDTO {
    private Long beforeAfterId;
    private Long memberId;
    private Long lessonId;
    private String title;
    private String beforeContentUrl;
    private String afterContentUrl;

    public static BeforeAfterDTO of(Long memberId, BeforeAfterRequest beforeAfterRequest, String beforeContentUrl, String afterContentUrl) throws JsonProcessingException {
        return BeforeAfterDTO.builder()
                .memberId(memberId)
                .lessonId(beforeAfterRequest.getLessonId())
                .title(beforeAfterRequest.getTitle())
                .beforeContentUrl(beforeContentUrl)
                .afterContentUrl(afterContentUrl)
                .build();
    }
}
