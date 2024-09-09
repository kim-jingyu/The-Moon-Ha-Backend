package com.innerpeace.themoonha.domain.schedule.dto;

import lombok.*;

/**
 * 스케줄 월간보기 응답 DTO
 * @author 조희정
 * @since 2024.09.09
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.09  	조희정       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleNextResponse {
    private Long lessonId;
    private String branchName;
    private String lessonTitle;
    private int cnt;
    private String period;
    private String lessonTime;
    private String tutorName;
    private String target;
    private Long loungeId;
    private boolean onlineYn;
}

