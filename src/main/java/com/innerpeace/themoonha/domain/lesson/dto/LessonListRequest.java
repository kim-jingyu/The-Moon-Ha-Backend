package com.innerpeace.themoonha.domain.lesson.dto;

import com.innerpeace.themoonha.domain.lesson.vo.LessonTime;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LessonListRequest {
    private String branchName;
    private String lessonTitle;
    private String tutorName;
    private String day;
    private Integer target;
    private String category;
    private Integer cnt;
    private Integer lessonTime;
    private String startTime;
    private String endTime;

    public void setLessonTime() {
        LessonTime selectedTime = LessonTime.findLessonTime(lessonTime);
        if (selectedTime != null) {
            this.startTime = selectedTime.getStartTime();
            this.endTime = selectedTime.getEndTime();
        }
    }
}
