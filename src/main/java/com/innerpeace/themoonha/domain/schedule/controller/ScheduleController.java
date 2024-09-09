package com.innerpeace.themoonha.domain.schedule.controller;

import com.innerpeace.themoonha.domain.schedule.dto.ScheduleMonthlyResponse;
import com.innerpeace.themoonha.domain.schedule.dto.ScheduleWeeklyResponse;
import com.innerpeace.themoonha.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 스케줄 컨트롤러
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
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 스케줄 주간 보기
     * @param standardDates
     * @return
     */
    @GetMapping("/weekly")
    public ResponseEntity<List<List<ScheduleWeeklyResponse>>> scheduleWeekly(@RequestParam List<String> standardDates) {
        Long memberId = 1L;
        return ResponseEntity.ok(scheduleService.findWeeklySchedules(memberId, standardDates));
    }

    /**
     * 스케줄 월간보기
     * @param yearMonth
     * @return
     */
    @GetMapping("/monthly")
    public ResponseEntity<List<ScheduleMonthlyResponse>>scheduleMonthly(@RequestParam String yearMonth) {
        Long memberId = 1L;
        return ResponseEntity.ok(scheduleService.findMonthlySchedules(memberId, yearMonth));
    }
}
