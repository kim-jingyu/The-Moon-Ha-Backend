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
    private String streamKey;
    private String broadcastUrl;
    private String thumbnailUrl;
    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;

    private static final String SHA_256 = "sha-256";

    public static LiveLesson createLiveLesson(LiveLessonRequest request, String thumbnailUrl) {
        String generatedStreamKey = generateStreamKey();
        return LiveLesson.builder()
                .memberId(request.getMemberId())
                .lessonId(request.getLessonId())
                .title(request.getTitle())
                .description(request.getDescription())
                .status(UPCOMING)
                .streamKey(generatedStreamKey)
                .broadcastUrl("/hls/" + generatedStreamKey +".m3u8")
                .thumbnailUrl(thumbnailUrl)
                .build();
    }

    public void startLiveLesson() {
        this.status = ON_AIR;
    }

    public void endLiveLesson() {
        this.status = ENDED;
    }

    private static String generateStreamKey() {
        try {
            StringBuilder hexString = new StringBuilder();
            for (byte b : MessageDigest.getInstance(SHA_256)
                    .digest(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8))) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(ErrorCode.LIVE_STREAM_KEY_CREATION_FAILED);
        }
    }
}
