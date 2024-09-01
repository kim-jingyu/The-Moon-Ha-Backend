package com.innerpeace.themoonha.domain.live.vo;

import com.innerpeace.themoonha.domain.live.dto.ChatMessage;
import lombok.*;

import static lombok.AccessLevel.*;

/**
 * LiveChat VO
 * @author 김진규
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	김진규       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class LiveChat {
    private Long liveChatId;
    private Long memberId;
    private Long liveId;
    private String content;

    public static LiveChat createLiveChat(ChatMessage chatMessage) {
        return LiveChat.builder()
                .liveId(chatMessage.getLiveId())
                .memberId(chatMessage.getMemberId())
                .content(chatMessage.getContent())
                .build();
    }
}
