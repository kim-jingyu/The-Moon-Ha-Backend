package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

/**
 * 강사 상세정보 진행중인 강좌 정보 DTO
 * @author 손승완
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	손승완       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TutorLessonDetailDTO {
    private String name;
    private String profileImgUrl;
    private Long lessonId;
    private String title;
    private String thumbnailUrl;
    private int cnt;
    private String endDate;
    private String lessonTime;
}
