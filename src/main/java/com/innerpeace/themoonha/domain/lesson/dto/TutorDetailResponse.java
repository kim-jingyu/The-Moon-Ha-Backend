package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 강사 상세정보 조회 응답 DTO
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
public class TutorDetailResponse {
    private String name;
    private String profileImgUrl;
    private List<String> endedLessonList;
    private List<TutorLessonDetailDTO> ongoingLessonList;

    public static TutorDetailResponse from(List<TutorLessonDetailDTO> tutorDetailList) {
        List<String> endedLessonList = new ArrayList<>();
        List<TutorLessonDetailDTO> ongoingLessonList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();

        for (TutorLessonDetailDTO tutorDetailDTO : tutorDetailList) {
            LocalDate lessonEndDate = LocalDate.parse(tutorDetailDTO.getEndDate(), formatter);
            if (lessonEndDate.isBefore(currentDate)) {
                String title = "[" + tutorDetailDTO.getEndDate() + "] " + tutorDetailDTO.getTitle();
                endedLessonList.add(title);
                continue;
            }
            ongoingLessonList.add(tutorDetailDTO);
        }

        return TutorDetailResponse.builder()
                .name(tutorDetailList.get(0).getName())
                .profileImgUrl(tutorDetailList.get(0).getProfileImgUrl())
                .endedLessonList(endedLessonList)
                .ongoingLessonList(ongoingLessonList)
                .build();
    }
}
