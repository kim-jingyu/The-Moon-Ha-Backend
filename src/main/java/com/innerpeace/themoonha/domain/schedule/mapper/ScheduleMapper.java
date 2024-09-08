package com.innerpeace.themoonha.domain.schedule.mapper;

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
 * </pre>
 */
public interface ScheduleMapper {

    /**
     * 주 단위로 스케줄 가져오기
     * @param memberId
     * @param standardDates
     * @return
     */
    List<ScheduleWeeklyResponse> selectWeeklySchedules(@Param("memberId") Long memberId, @Param("standardDates") List<String> standardDates);
}
