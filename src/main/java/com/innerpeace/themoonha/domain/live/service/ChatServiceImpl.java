package com.innerpeace.themoonha.domain.live.service;

import com.innerpeace.themoonha.domain.live.dto.ChatMessage;
import com.innerpeace.themoonha.domain.live.mapper.LiveChatMapper;
import com.innerpeace.themoonha.domain.live.mapper.LiveLessonMapper;
import com.innerpeace.themoonha.domain.live.vo.LiveChat;
import com.innerpeace.themoonha.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.innerpeace.themoonha.global.exception.ErrorCode.LIVE_CHAT_MESSAGE_NOT_FOUND;

/**
 * 실시간 강좌 채팅 서비스 구현체
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
@Transactional
@RequiredArgsConstructor
@EnableScheduling
public class ChatServiceImpl implements ChatService {
    private static final String CHAT_PREFIX = "chat:";

    private final RedisTemplate<String, Object> redisTemplate;
    private final LiveChatMapper liveChatMapper;
    private final LiveLessonMapper liveLessonMapper;

    @Override
    public void saveChatMessage(ChatMessage chatMessage) {
        redisTemplate.opsForList().rightPush(CHAT_PREFIX + chatMessage.getLiveId(), chatMessage);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ChatMessage> getChatMessages(Long liveId) {
        List<Object> result = redisTemplate.opsForList().range(CHAT_PREFIX + liveId, 0, -1);
        return result != null ? (List<ChatMessage>) (List<?>) result : new ArrayList<>();
    }

    public void clearChatMessages(Long liveId) {
        redisTemplate.delete(CHAT_PREFIX + liveId);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void batchSavedChatMessages() {
        liveLessonMapper.findActiveLiveLessonIdList()
                .forEach(liveId -> {
                    List<ChatMessage> chatMessages = getChatMessages(liveId);
                    if (chatMessages.isEmpty()) throw new CustomException(LIVE_CHAT_MESSAGE_NOT_FOUND);
                    chatMessages.forEach(chatMessage -> {
                        liveChatMapper.insertLiveChat(LiveChat.createLiveChat(chatMessage));
                    });
                    clearChatMessages(liveId);
                });
    }
}
