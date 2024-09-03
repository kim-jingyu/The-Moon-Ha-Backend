package com.innerpeace.themoonha.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 실시간 강좌 이벤트 프로듀서
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  김진규        최초 생성
 * </pre>
 * @since 2024.09.01
 */
@Service
@RequiredArgsConstructor
public class LiveLessonEventService {
    protected static final String VIEW_JOINED = "view-joined";
    protected static final String LIVE_LESSONS_EVENTS = "live-lessons-events";
    protected static final String VIEW_LEFT = "view-left";
    protected static final String LIKE = "like";
    protected static final String LIVE_STATUS = "live-status";
    protected static final String LIVE_LESSONS_STATUS = "live-lessons-status";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendViewerJoinedEvent(Long liveId, Long memberId) {
        kafkaTemplate.send(LIVE_LESSONS_EVENTS, getViewJoined(liveId, memberId));
    }

    public void sendViewerLeftEvent(Long liveId, Long memberId) {
        kafkaTemplate.send(LIVE_LESSONS_EVENTS, getViewLeft(liveId, memberId));
    }

    public void sendLikeEvent(Long liveId, Long memberId) {
        kafkaTemplate.send(LIVE_LESSONS_EVENTS, getLike(liveId, memberId));
    }

    public void sendStatusEvent(Long liveId, String status) {
        kafkaTemplate.send(LIVE_LESSONS_STATUS, getLiveStatus(liveId, status));
    }

    private String getLiveStatus(Long liveId, String status) {
        return LIVE_STATUS + ":" + liveId + ":" + status;
    }

    private String getViewJoined(Long liveId, Long memberId) {
        return VIEW_JOINED + ":" + liveId + ":" + memberId;
    }

    private String getViewLeft(Long liveId, Long memberId) {
        return VIEW_LEFT + ":" + liveId + ":" + memberId;
    }

    private String getLike(Long liveId, Long memberId) {
        return LIKE + ":" + liveId + ":" + memberId;
    }
}
