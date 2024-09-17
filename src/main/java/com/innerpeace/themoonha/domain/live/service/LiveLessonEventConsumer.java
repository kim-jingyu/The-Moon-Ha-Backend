package com.innerpeace.themoonha.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.innerpeace.themoonha.domain.live.service.LiveLessonEventService.*;

/**
 * 실시간 강좌 이벤트 컨슈머
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
public class LiveLessonEventConsumer {
    private static final String LIVE = "live";
    private static final String VIEWERS = "viewers";
    private static final String LIKES = "likes";

    private final RedisTemplate<String, Object> redisTemplate;

    @KafkaListener(topics = LIVE_LESSONS_EVENTS, groupId = "live-lessons-group")
    public void processEvents(String message) {
        String[] splitMessage = getSplitMessage(message);
        String eventType = getEventType(splitMessage);
        long liveId = getLiveId(splitMessage);
        long memberId = getMemberId(splitMessage);

        String viewersKey = getViewersKey(liveId);
        String likesKey = getLikesKey(liveId);

        switch (eventType) {
            case VIEW_JOINED:
                redisTemplate.opsForValue().increment(viewersKey);
                break;
            case VIEW_LEFT:
                redisTemplate.opsForValue().decrement(viewersKey);
                break;
            case LIKE:
                int currentLikes = getLikesCount(liveId);
                if (currentLikes > 0) {
                    redisTemplate.opsForValue().decrement(likesKey);
                } else {
                    redisTemplate.opsForValue().increment(likesKey);
                }
                break;
        }
    }

    public int getViewersCount(Long livedId) {
        Integer count = (Integer) redisTemplate.opsForValue().get(getViewersKey(livedId));
        return count != null ? count : 0;
    }

    public int getLikesCount(Long liveId) {
        Integer count = (Integer) redisTemplate.opsForValue().get(getLikesKey(liveId));
        return count != null ? count : 0;
    }

    public void removeViewers(Long liveId) {
        redisTemplate.delete(getViewersKey(liveId));
    }

    public void removeLikes(Long liveId) {
        redisTemplate.delete(getLikesKey(liveId));
    }

    private String[] getSplitMessage(String message) {
        return message.split(":");
    }

    private String getEventType(String[] splitMessage) {
        return splitMessage[0];
    }

    private long getLiveId(String[] splitMessage) {
        return Long.parseLong(splitMessage[1]);
    }

    private long getMemberId(String[] splitMessage) {
        return Long.parseLong(splitMessage[2]);
    }

    private String getLikesKey(long liveId) {
        return LIVE + ":" + liveId + ":" + LIKES;
    }

    private String getViewersKey(long liveId) {
        return LIVE + ":" + liveId + ":" + VIEWERS;
    }
}
