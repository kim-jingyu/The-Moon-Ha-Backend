package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TutorLessonResponse {
    private Long lessonId;
    private String lessonTitle;
}
