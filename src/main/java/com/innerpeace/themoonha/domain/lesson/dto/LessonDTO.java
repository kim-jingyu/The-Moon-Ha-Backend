package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LessonDTO {
    private Long lessonId;
    private String thumbnailUrl;
    private String target;
    private String title;
    private int cnt;
    private int cost;
    private String tutorName;
    private String lessonTime;
}
