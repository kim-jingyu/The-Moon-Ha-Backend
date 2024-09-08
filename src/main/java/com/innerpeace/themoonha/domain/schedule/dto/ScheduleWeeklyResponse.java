package com.innerpeace.themoonha.domain.schedule.dto;

import lombok.*;

/**
 * 스케줄 주별보기 응답 DTO
 * @author 조희정
 * @since 2024.09.07
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.07  	조희정       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleWeeklyResponse {
    private Long lessonId;
    private String day;
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
    private String title;
    private String standardDate;
}
