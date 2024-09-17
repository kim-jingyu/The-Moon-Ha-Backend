package com.innerpeace.themoonha.domain.live.dto;

import com.innerpeace.themoonha.domain.live.vo.LiveStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static lombok.AccessLevel.PROTECTED;

/**
 * 실시간 강좌 상세정보 Response
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
public class LiveLessonDetailResponse {
    private Long liveId;
    private String title;
    private String description;
    private String profileImgUrl;
    private String instructorName;
    private String thumbnailUrl;
    @Setter
    private String broadcastUrl;
    private LiveStatus status;
    private Date createdAt;
    private Long minutesAgo;
    private Boolean isEnrolled;
    private String summary;
    private String curriculum;
    private String supply;
}
