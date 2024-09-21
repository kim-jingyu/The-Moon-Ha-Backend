package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 강사의 강좌 목록 조회 응답 DTO
 *
 * @author 손승완
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.09  	손승완       최초 생성
 * </pre>
 * @since 2024.09.09
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TutorLessonResponse {
    private Long lessonId;
    private String lessonTitle;
}
