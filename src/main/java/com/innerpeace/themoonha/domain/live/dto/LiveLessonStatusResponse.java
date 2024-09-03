package com.innerpeace.themoonha.domain.live.dto;

import com.innerpeace.themoonha.domain.live.vo.LiveLesson;
import com.innerpeace.themoonha.domain.live.vo.LiveStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

/**
 * 실시간 강좌 DTO
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
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class LiveLessonStatusResponse {
    private Long liveId;
    private LiveStatus status;

    public static LiveLessonStatusResponse from(LiveLesson liveLesson) {
        return new LiveLessonStatusResponse(liveLesson.getLiveId(), liveLesson.getStatus());
    }
}
