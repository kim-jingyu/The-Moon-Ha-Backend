package com.innerpeace.themoonha.domain.lesson.mapper;

import com.innerpeace.themoonha.domain.lesson.dto.*;

import java.util.List;
import java.util.Optional;

/**
 * 강좌 매퍼
 * @author 손승완
 * @since 2024.08.24
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.24  	손승완       최초 생성
 * 2024.08.25   손승완       강좌 상세보기 기능 추가
 * 2024.08.26   손승완       강사 상세보기 기능 추가
 * </pre>
 */
public interface LessonMapper {
    Optional<LessonListResponse> selectLessonList(LessonListRequest lessonListRequest);
    Optional<LessonDetailResponse> selectLessonDetail(Long lessonId);
    Optional<ShortFormDetailResponse> selectShortFormDetail(Long shortFormId);
    List<TutorLessonDetailDTO> findTutorDetail(Long tutorId);
}
