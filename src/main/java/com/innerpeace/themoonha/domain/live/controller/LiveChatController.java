package com.innerpeace.themoonha.domain.live.controller;

import com.innerpeace.themoonha.domain.live.dto.ChatMessage;
import com.innerpeace.themoonha.domain.live.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 실시간 강좌 채팅 컨트롤러
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
@Configuration
@RequestMapping("/live")
@RequiredArgsConstructor
public class LiveChatController {
    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatService.saveChatMessage(chatMessage);
        return chatMessage;
    }

    @GetMapping("/live/{liveId}/chats")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@Payload Long liveId) {
        return ResponseEntity.ok(chatService.getChatMessages(liveId));
    }
}
