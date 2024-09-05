package com.innerpeace.themoonha.domain.admin.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LessonBranchDTO {
    private Long label;
    private List<LessonIndexDTO> items;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public static class LessonIndexDTO{
        private Long index;
        private String name;
    }
}
