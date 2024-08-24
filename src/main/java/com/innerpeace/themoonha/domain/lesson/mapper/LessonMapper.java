package com.innerpeace.themoonha.domain.lesson.mapper;

import com.innerpeace.themoonha.domain.lesson.dto.LessonDTO;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListRequest;

import java.util.List;

public interface LessonMapper {
    List<LessonDTO> selectLessonList(LessonListRequest lessonListRequest);
}
