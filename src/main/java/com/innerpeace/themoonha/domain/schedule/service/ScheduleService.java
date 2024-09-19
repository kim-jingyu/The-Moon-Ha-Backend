package com.innerpeace.themoonha.domain.schedule.service;

import com.innerpeace.themoonha.domain.schedule.dto.ScheduleMonthlyResponse;
import com.innerpeace.themoonha.domain.schedule.dto.ScheduleNextResponse;
import com.innerpeace.themoonha.domain.schedule.dto.ScheduleWeeklyResponse;

import java.util.List;
import java.util.Optional;

/**
 * 스케줄 서비스 인터페이스
 * @author 조희정
 * @since 2024.09.07
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.07  	조희정       최초 생성
 * 2024.09.07  	조희정       스케줄 주간 보기 구현
 * 2024.09.09  	조희정       스케줄 월간 보기 구현
 * 2024.09.09  	조희정       댜음 강좌 보기 구현
 * </pre>
 */
public interface ScheduleService {

    /**
     * 스케줄 주간 보기
     * @param memberId
     * @param standardDate
     * @return
     */
    List<List<ScheduleWeeklyResponse>> findWeeklySchedules(Long memberId, List<String> standardDate, String role);

    /**
     * 스케줄 월간 보기
     * @param memberId
     * @param yearMonth
     * @return
     */
    List<ScheduleMonthlyResponse> findMonthlySchedules(Long memberId, String yearMonth, String role);

    /**
     * 다음 스케줄 조회
     * @param memberId
     * @return
     */
    ScheduleNextResponse findNextSchedule(Long memberId, String role);
}
