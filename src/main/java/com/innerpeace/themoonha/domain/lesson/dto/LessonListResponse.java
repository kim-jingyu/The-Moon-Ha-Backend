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
 * 2024.08.30  	손승완       정적 팩토리 메서드 패턴 추가 및 setter 제거
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LessonListResponse {
    private Long branchId;
    private List<ShortFormDTO> shortFormList;
    private String memberName;
    private List<LessonDTO> lessonList;

    public static LessonListResponse of(LessonListDTO lessonList, List<ShortFormDTO> shortFormList,
                                        String memberName) {

        return LessonListResponse.builder()
                .lessonList(lessonList.getLessonList())
                .branchId(lessonList.getBranchId())
                .shortFormList(shortFormList)
                .memberName(memberName)
                .build();
    }
}
