package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;


/**
 * 강좌 목록 내부 강좌 정보 DTO
 * @author 손승완
 * @since 2024.08.24
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.24  	손승완       최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LessonDTO {
    private Long lessonId;
    private String thumbnailUrl;
    private String target;
    private String title;
    private int cnt;
    private int cost;
    private String tutorName;
    private String lessonTime;
}
