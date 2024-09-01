package com.innerpeace.themoonha.domain.live.dto;

import com.innerpeace.themoonha.domain.live.vo.LiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

/**
 * LiveLesson Request
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
public class LiveLessonRequest {
    private Long memberId;
    private Long lessonId;
    private String title;
    private String description;
    private LiveStatus status;
    private String streamKey;
    private String thumbnailUrl;
}
