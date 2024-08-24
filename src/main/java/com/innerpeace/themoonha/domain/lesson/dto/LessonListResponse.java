package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LessonListResponse {
    private String branchName;
    private List<ShortFormDTO> shortFormList;
    private String memberName;
    private List<LessonDTO> lessonList;

    public static LessonListResponse of(List<LessonDTO> lessonList,
                                        List<ShortFormDTO> shortFormList,
                                        String memberName,
                                        String branchName) {

        return LessonListResponse.builder()
                .lessonList(lessonList)
                .shortFormList(shortFormList)
                .memberName(memberName)
                .branchName(branchName)
                .build();
    }
}
