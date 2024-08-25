package com.innerpeace.themoonha.domain.lesson.service;

import com.innerpeace.themoonha.domain.lesson.dto.LessonDetailResponse;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListRequest;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListResponse;

/**
 * 강좌 서비스 인터페이체
 * @author 손승완
 * @since 2024.08.24
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.24  	손승완       최초 생성
 * 2024.08.25   손승완       강좌 상세보기 기능 추가
 * </pre>
 */
public interface LessonService {
    LessonListResponse findLessonList(LessonListRequest lessonListRequest);

    LessonDetailResponse findLessonDetail(Long lessonId);

}
