package com.innerpeace.themoonha.domain.bite.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BeforeAfterDTO {
    private Long memberId;
    private Long lessonId;
    private String title;
    private String beforeContentUrl;
    private String afterContentUrl;
    private List<String> hashtags = new ArrayList<>();

    public static BeforeAfterDTO of(Long memberId, BeforeAfterRequest beforeAfterRequest, String beforeContentUrl, String afterContentUrl) {
        BeforeAfterDTO dto = BeforeAfterDTO.builder()
                .memberId(memberId)
                .lessonId(beforeAfterRequest.getLessonId())
                .title(beforeAfterRequest.getTitle())
                .beforeContentUrl(beforeContentUrl)
                .afterContentUrl(afterContentUrl)
                .build();
        for (String hashtag : beforeAfterRequest.getHashtags()) {
            dto.getHashtags().add(hashtag);
        }
        return dto;
    }
}
