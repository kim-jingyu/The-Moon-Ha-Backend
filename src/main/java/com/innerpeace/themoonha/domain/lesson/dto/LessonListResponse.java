package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LessonListResponse {
    private String branchName;
    private List<ShortFormDTO> shortFormList;
    private String memberName;
    private List<LessonDTO> lessonList;
}
