package com.innerpeace.themoonha.domain.lesson.scheduler;

import com.innerpeace.themoonha.domain.lesson.dto.ShortFormViewCountDTO;
import com.innerpeace.themoonha.domain.lesson.service.LessonService;
import com.innerpeace.themoonha.global.service.RedisViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class LessonScheduler {
    private static final String SHORTFORM_KEY_PATTERN = "shortForm:*";
    private final LessonService lessonService;
    private final RedisViewService redisViewService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void updateViewCounts() {
        log.info("밤 12시, 조회수 업데이트 스케줄링 작업을 실행합니다");
        Map<Long, Integer> viewCountMap = redisViewService.getDomainViewCounts(SHORTFORM_KEY_PATTERN);
        if (viewCountMap.isEmpty()) return;

        List<ShortFormViewCountDTO> viewCountList = new ArrayList<>();
        for (Long key : viewCountMap.keySet()) {
            viewCountList.add(new ShortFormViewCountDTO(key, viewCountMap.get(key)));
        }

        lessonService.increaseShortFormViewCountDB(viewCountList);
        redisViewService.clearDomainCache(SHORTFORM_KEY_PATTERN);
    }
}
