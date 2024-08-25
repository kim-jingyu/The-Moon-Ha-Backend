package com.innerpeace.themoonha.domain.lesson.service;

import com.innerpeace.themoonha.domain.lesson.dto.LessonListRequest;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListResponse;
import com.innerpeace.themoonha.domain.lesson.mapper.LessonMapper;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonServiceImpl implements LessonService {
    private final LessonMapper lessonMapper;

    @Override
    public LessonListResponse findLessonList(LessonListRequest lessonListRequest) {
        String memberName = "고객1"; // 임시값
        lessonListRequest.setLessonTime();
        LessonListResponse lessonListResponse = lessonMapper.selectLessonList(lessonListRequest)
                .orElseThrow(() -> new CustomException(ErrorCode.LESSON_NOT_FOUND));
        lessonListResponse.setMemberName(memberName);
        return lessonListResponse;
    }
}
