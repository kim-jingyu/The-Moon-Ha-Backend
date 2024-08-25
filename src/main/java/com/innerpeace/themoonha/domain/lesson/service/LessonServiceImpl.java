package com.innerpeace.themoonha.domain.lesson.service;

import com.innerpeace.themoonha.domain.lesson.dto.LessonDetailResponse;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListRequest;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListResponse;
import com.innerpeace.themoonha.domain.lesson.dto.ShortFormDetailResponse;
import com.innerpeace.themoonha.domain.lesson.mapper.LessonMapper;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 강좌 서비스 구현체
 * @author 손승완
 * @since 2024.08.24
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.24  	손승완       최초 생성
 * 2024.08.25   손승완       강좌 상세보기 기능 추가
 * 2024.08.25   손승완       숏폼 상세보기 기능 추가
 * </pre>
 */
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

    @Override
    public LessonDetailResponse findLessonDetail(Long lessonId) {
        return lessonMapper.selectLessonDetail(lessonId)
                .orElseThrow(() -> new CustomException(ErrorCode.LESSON_NOT_FOUND));
    }

    @Override
    public ShortFormDetailResponse findShortFormDetail(Long shortFormId) {
        return lessonMapper.selectShortFormDetail(shortFormId)
                .orElseThrow(() -> new CustomException(ErrorCode.SHORTFORM_NOT_FOUND));
    }
}
