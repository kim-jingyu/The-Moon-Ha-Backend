package com.innerpeace.themoonha.domain.lesson.mapper;

import com.innerpeace.themoonha.domain.lesson.dto.LessonDetailResponse;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListRequest;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListResponse;

import java.util.Optional;

public interface LessonMapper {
    Optional<LessonListResponse> selectLessonList(LessonListRequest lessonListRequest);

    Optional<LessonDetailResponse> selectLessonDetail(Long lessonId);
}
