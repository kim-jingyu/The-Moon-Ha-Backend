package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 강좌 상세 응답 DTO
 * @author 손승완
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25   손승완       최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LessonDetailResponse {
    private Long lessonId;
    private String title;
    private String branchName;
    private String period;
    private String lessonTime;
    private int cnt;
    private String place;
    private String tutorName;
    private int cost;
    private Integer onlineCost;
    private String summary;
    private String curriculum;
    private String supply;
    private String thumbnailUrl;
    private String previewVideoUrl;
    private int tutorId;
    private String tutorProfileImgUrl;
}
