package com.innerpeace.themoonha.domain.schedule.controller;

import com.innerpeace.themoonha.domain.schedule.dto.ScheduleMonthlyResponse;
import com.innerpeace.themoonha.domain.schedule.dto.ScheduleNextResponse;
import com.innerpeace.themoonha.domain.schedule.dto.ScheduleWeeklyResponse;
import com.innerpeace.themoonha.domain.schedule.service.ScheduleService;
import com.innerpeace.themoonha.global.util.MemberId;
import com.innerpeace.themoonha.global.util.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
 * 2024.09.09  	조희정       다음 스케줄 보기 기능 구현
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
    public ResponseEntity<List<List<ScheduleWeeklyResponse>>> scheduleWeekly(@RequestParam List<String> standardDates, @MemberId Long memberId, @Role String role) {
        return ResponseEntity.ok(scheduleService.findWeeklySchedules(memberId, standardDates, role));
    }

    /**
     * 스케줄 월간보기
     * @param yearMonth
     * @return
     */
    @GetMapping("/monthly")
    public ResponseEntity<List<ScheduleMonthlyResponse>>scheduleMonthly(@RequestParam String yearMonth, @MemberId Long memberId, @Role String role) {
        return ResponseEntity.ok(scheduleService.findMonthlySchedules(memberId, yearMonth, role));
    }

    /**
     * 다음 스케줄 보기
     * @return
     */
    @GetMapping("/next")
    public ResponseEntity<ScheduleNextResponse>scheduleNext(@MemberId Long memberId, @Role String role) {
        return ResponseEntity.ok(scheduleService.findNextSchedule(memberId, role));
    }
}
