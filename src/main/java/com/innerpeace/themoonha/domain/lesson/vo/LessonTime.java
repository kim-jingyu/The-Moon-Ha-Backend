package com.innerpeace.themoonha.domain.lesson.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum LessonTime {
    FIRST_TIME(1, "07", "09"),
    SECOND_TIME(2, "09", "11"),
    THIRD_TIME(3, "11", "13"),
    FOURTH_TIME(4, "13", "15"),
    FIFTH_TIME(5, "15", "17"),
    SIXTH_TIME(6, "17", "19"),
    SEVENTH_TIME(7, "19", "21");

    private final Integer value;
    private final String startTime;
    private final String endTime;

    public static LessonTime findLessonTime(Integer value) {
        return Arrays.stream(LessonTime.values())
                .filter(lessonTime -> lessonTime.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
