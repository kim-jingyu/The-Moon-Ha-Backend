package com.innerpeace.themoonha.domain.lesson.service;

import com.innerpeace.themoonha.domain.lesson.dto.LessonDTO;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListRequest;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListResponse;
import com.innerpeace.themoonha.domain.lesson.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonServiceImpl implements LessonService {
    private final LessonMapper lessonMapper;
    @Override
    public LessonListResponse findLessonList(LessonListRequest lessonListRequest) {
        lessonListRequest.setLessonTime();
        List<LessonDTO> lessonList = lessonMapper.selectLessonList(lessonListRequest);
        return LessonListResponse.of(
                lessonList,
                null,
                null,
                lessonListRequest.getBranchName());
    }
}
