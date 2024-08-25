package com.innerpeace.themoonha.domain.lesson.service;

import com.innerpeace.themoonha.domain.lesson.dto.LessonListRequest;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListResponse;

public interface LessonService {
    LessonListResponse findLessonList(LessonListRequest lessonListRequest);
}
