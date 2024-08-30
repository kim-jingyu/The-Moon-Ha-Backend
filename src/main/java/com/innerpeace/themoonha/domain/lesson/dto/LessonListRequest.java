package com.innerpeace.themoonha.domain.lesson.dto;

import com.innerpeace.themoonha.domain.lesson.vo.LessonTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 강좌 목록 요청 DTO
 *
 * @author 손승완
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	손승완       최초 생성
 * </pre>
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LessonListRequest {
    private Long branchId;
    private String lessonTitle;
    private String tutorName;
    private String day;
    private Integer target;
    private Long categoryId;
    private Integer cnt;
    private Integer lessonTime;
    private String startTime;
    private String endTime;

    public LessonListRequest(Long branchId, String lessonTitle, String tutorName, String day,
                             Integer target, Long categoryId, Integer cnt, Integer lessonTime) {
        this.branchId = branchId;
        this.lessonTitle = lessonTitle;
        this.tutorName = tutorName;
        this.day = day;
        this.target = target;
        this.categoryId = categoryId;
        this.cnt = cnt;
        this.lessonTime = lessonTime;
        setLessonTime();
    }

    private void setLessonTime() {
        LessonTime selectedTime = LessonTime.findLessonTime(lessonTime);
        if (selectedTime != null) {
            this.startTime = selectedTime.getStartTime();
            this.endTime = selectedTime.getEndTime();
        }
    }
}
