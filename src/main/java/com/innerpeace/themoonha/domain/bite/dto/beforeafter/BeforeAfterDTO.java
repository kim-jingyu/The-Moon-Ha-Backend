package com.innerpeace.themoonha.domain.bite.dto.beforeafter;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.*;

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
    private String beforeContentThumbnailUrl;
    private String afterContentThumbnailUrl;
    private String beforeContentUrl;
    private String afterContentUrl;

    public static BeforeAfterDTO of(Long memberId, BeforeAfterRequest beforeAfterRequest, String beforeContentThumbnailUrl, String afterContentThumbnailUrl, String beforeContentUrl, String afterContentUrl) throws JsonProcessingException {
        return BeforeAfterDTO.builder()
                .memberId(memberId)
                .lessonId(beforeAfterRequest.getLessonId())
                .title(beforeAfterRequest.getTitle())
                .beforeContentThumbnailUrl(beforeContentThumbnailUrl)
                .afterContentThumbnailUrl(afterContentThumbnailUrl)
                .beforeContentUrl(beforeContentUrl)
                .afterContentUrl(afterContentUrl)
                .build();
    }
}
