package com.innerpeace.themoonha.domain.live.vo;

import com.innerpeace.themoonha.domain.live.dto.LiveLessonRequest;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import static com.innerpeace.themoonha.domain.live.vo.LiveStatus.*;
import static lombok.AccessLevel.PROTECTED;

/**
 * LiveLesson VO
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
public class LiveLesson {
    private Long liveId;
    private Long memberId;
    private Long lessonId;
    private String title;
    private String description;
    private LiveStatus status;
    private String thumbnailUrl;
    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;

    public static LiveLesson createLiveLesson(Long memberId, LiveLessonRequest request, String thumbnailUrl) {
        return LiveLesson.builder()
                .memberId(memberId)
                .lessonId(request.getLessonId())
                .title(request.getTitle())
                .description(request.getDescription())
                .status(UPCOMING)
                .thumbnailUrl(thumbnailUrl)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public void startLiveLesson() {
        this.status = ON_AIR;
    }

    public void endLiveLesson() {
        this.status = ENDED;
    }
}
