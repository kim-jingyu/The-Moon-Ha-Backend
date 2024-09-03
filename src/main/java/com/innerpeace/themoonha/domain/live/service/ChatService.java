package com.innerpeace.themoonha.domain.live.service;

import com.innerpeace.themoonha.domain.live.dto.ChatMessage;

import java.util.List;

/**
 * 실시간 강좌 채팅 서비스
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
public interface ChatService {
    void saveChatMessage(ChatMessage chatMessage);
    List<ChatMessage> getChatMessages(Long liveId);
}
