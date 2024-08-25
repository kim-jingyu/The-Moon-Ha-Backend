package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

import java.util.List;

/**
 * 강좌 목록 응답 DTO
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
public class LessonListResponse {
    private String branchName;
    private List<ShortFormDTO> shortFormList;
    private String memberName;
    private List<LessonDTO> lessonList;
}
