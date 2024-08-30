package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

import java.util.List;

/**
 * 강좌 목록 조회 쿼리 DTO
 *
 * @author 손승완
 * @since 2024.08.30
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.30  	손승완       최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LessonListDTO {
    private Long branchId;
    private List<LessonDTO> lessonList;
}
