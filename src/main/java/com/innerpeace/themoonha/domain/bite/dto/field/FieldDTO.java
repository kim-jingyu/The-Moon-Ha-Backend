package com.innerpeace.themoonha.domain.bite.dto.field;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.*;

/**
 * 분야별 한 입 DTO
 * @author 김진규
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31   김진규      최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FieldDTO {
    private Long fieldId;
    private Long memberId;
    private Long lessonId;
    private String title;
    private String thumbnailUrl;
    private String contentUrl;

    public static FieldDTO of(Long memberId, FieldRequest fieldRequest, String thumbnailUrl, String contentUrl) throws JsonProcessingException {
        return FieldDTO.builder()
                .memberId(memberId)
                .lessonId(fieldRequest.getLessonId())
                .title(fieldRequest.getTitle())
                .thumbnailUrl(thumbnailUrl)
                .contentUrl(contentUrl)
                .build();
    }
}
