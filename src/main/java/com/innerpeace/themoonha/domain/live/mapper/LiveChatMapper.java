package com.innerpeace.themoonha.domain.live.mapper;

import com.innerpeace.themoonha.domain.live.vo.LiveChat;

/**
 * 실시간 강좌 채팅 매퍼
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
public interface LiveChatMapper {
    void insertLiveChat(LiveChat liveChat);
}
