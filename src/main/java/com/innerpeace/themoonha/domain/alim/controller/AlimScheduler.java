package com.innerpeace.themoonha.domain.alim.controller;


import com.innerpeace.themoonha.domain.alim.dto.LoungeAlimDTO;
import com.innerpeace.themoonha.domain.alim.service.AlimService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.util.FcmUtil;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 알림 스케줄러
 * @author 조희정
 * @since 2024.09.11
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.11  	조희정       최초 생성
 * 2024.09.11  	조희정       수업 시작 5분전 알림 기능 구현
 * 2024.09.12  	조희정       라운지 오픈 후 알림 기능 구현
 * </pre>
 */
@Component
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class AlimScheduler {

    private final AlimService alimService;
    private final FcmUtil fcmUtil;

    /**
     * 수업 시작 5분점 알림
     */
//    @Scheduled(cron = "0 */5 * * * *")
    public void sendAlimForUpcomingLesson() {
        List<LoungeAlimDTO> loungePostAlim = alimService.getFcmTokenForUpcomingLessons();

        for (LoungeAlimDTO postAlim : loungePostAlim) {
            try {
                String lessonTitle = postAlim.getLessonTitle();
                String title = String.format("%s 수업 시작", lessonTitle);
                String message = String.format("곧 %s 수업이 시작됩니다!", lessonTitle);
                fcmUtil.send_FCM(postAlim.getFcmToken(), title, message);
            } catch (Exception e) {
                log.error("FCM 알림 전송 실패: " + e.getMessage(), e);
                throw new CustomException(ErrorCode.ALIM_SEND_FAIL);
            }
            CommonResponse.of(true, SuccessCode.ALIM_SEND_SUCCESS.getMessage());
        }
    }

    /**
     * 라운지 오픈하고 알림 보내기
     */
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void addLoungeAndSendAlim() {
        List<LoungeAlimDTO> loungeAddAlim = alimService.addLoungeAndSendAlim();

        if (!loungeAddAlim.isEmpty()) {
            for (LoungeAlimDTO loungeAlimDTO : loungeAddAlim) {
                try {
                    String lessonTitle = loungeAlimDTO.getLessonTitle();
                    String title = String.format("%s 강좌 라운지 오픈", lessonTitle);
                    String message = String.format("%s 강좌 라운지가 오픈되었습니다!", lessonTitle);
                    fcmUtil.send_FCM(loungeAlimDTO.getFcmToken(), title, message);
                } catch (Exception e) {
                    log.error("FCM 알림 전송 실패: " + e.getMessage(), e);
                    throw new CustomException(ErrorCode.ALIM_SEND_FAIL);
                }
                CommonResponse.of(true, SuccessCode.ALIM_SEND_SUCCESS.getMessage());
            }
        }
    }
}
