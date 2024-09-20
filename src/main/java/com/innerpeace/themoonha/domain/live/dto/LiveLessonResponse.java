package com.innerpeace.themoonha.domain.live.dto;

import com.innerpeace.themoonha.domain.live.vo.LiveLesson;
import com.innerpeace.themoonha.domain.live.vo.LiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import static lombok.AccessLevel.PROTECTED;

/**
 * 실시간 강좌 목록 Response
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
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class LiveLessonResponse {
    private Long liveId;
    private Long lessonId;
    private String title;
    private String description;
    private String profileImgUrl;
    private String instructorName;
    private String thumbnailUrl;
    private LiveStatus status;
    private Long minutesAgo;

    public static LiveLessonResponse of(LiveLesson liveLesson, String instructorName, String profileImgUrl) {
        return LiveLessonResponse.builder()
                .liveId(liveLesson.getLiveId())
                .title(liveLesson.getTitle())
                .description(liveLesson.getDescription())
                .profileImgUrl(profileImgUrl)
                .instructorName(instructorName)
                .thumbnailUrl(liveLesson.getThumbnailUrl())
                .status(liveLesson.getStatus())
                .minutesAgo(calculateMinutesSinceStart(liveLesson.getCreatedAt()))
                .build();
    }

    private static Long calculateMinutesSinceStart(Date startTIme) {
        return (System.currentTimeMillis() - startTIme.getTime()) / (1000 * 60);
    }
}
