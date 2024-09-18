package com.innerpeace.themoonha.domain.schedule.mapper;

import com.innerpeace.themoonha.domain.schedule.dto.ScheduleMonthlyResponse;
import com.innerpeace.themoonha.domain.schedule.dto.ScheduleNextResponse;
import com.innerpeace.themoonha.domain.schedule.dto.ScheduleWeeklyResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 스케줄 매퍼
 * @author 조희정
 * @since 2024.09.07
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.07  	조희정       최초 생성
 * 2024.09.07  	조희정       스케줄 주간 보기 기능 구현
 * 2024.09.09  	조희정       스케줄 월간 보기 기능 구현
 * 2024.09.09  	조희정       다음 강의 조회 기능 구현
 * </pre>
 */
public interface ScheduleMapper {

    /**
     * 주 단위로 스케줄 가져오기
     * @param memberId
     * @param standardDates
     * @return
     */
    List<ScheduleWeeklyResponse> selectWeeklySchedules(@Param("memberId") Long memberId, @Param("standardDates") List<String> standardDates, @Param("role") String role);

    /**
     * 월 단위로 스케줄 가져오기
     * @param memberId
     * @param yearMonth
     * @return
     */
    List<ScheduleMonthlyResponse> selectMonthlySchedules(@Param("memberId") Long memberId, @Param("yearMonth") String yearMonth, @Param("role") String role);

    /**
     * 다음 강의 조회
     * @param memberId
     * @return
     */
    ScheduleNextResponse selectNextSchedule(@Param("memberId") Long memberId, @Param("role") String role);
}
