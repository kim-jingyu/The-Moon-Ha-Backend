package com.innerpeace.themoonha.domain.bite.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BeforeAfterDTO {
    private Long baId;
    private Long memberId;
    private Long lessonId;
    private String title;
    private String beforeContentUrl;
    private String afterContentUrl;
    private List<String> hashtags;

    public static BeforeAfterDTO of(Long memberId, BeforeAfterRequest beforeAfterRequest, String beforeContentUrl, String afterContentUrl) throws JsonProcessingException {
        return BeforeAfterDTO.builder()
                .memberId(memberId)
                .lessonId(beforeAfterRequest.getLessonId())
                .title(beforeAfterRequest.getTitle())
                .beforeContentUrl(beforeContentUrl)
                .afterContentUrl(afterContentUrl)
                .hashtags(beforeAfterRequest.getHashtags())
                .build();
    }
}
