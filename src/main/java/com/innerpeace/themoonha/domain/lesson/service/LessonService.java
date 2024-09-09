package com.innerpeace.themoonha.domain.lesson.service;

import com.innerpeace.themoonha.domain.lesson.dto.*;
import com.innerpeace.themoonha.global.dto.CommonResponse;

import java.util.List;

/**
 * 강좌 서비스 인터페이스
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
 * 2024.08.26   손승완       강사 상세보기 기능 추가
 * 2024.08.26   손승완       장바구니 및 신청 기능 추가
 * </pre>
 */
public interface LessonService {
    LessonListResponse findLessonList(LessonListRequest lessonListRequest);
    LessonDetailResponse findLessonDetail(Long lessonId);
    TutorDetailResponse findTutorDetail(Long tutorId);
    List<CartResponse> findCartList(Long memberId);
    CommonResponse addCart(CartRequest cartRequest);
    CommonResponse payLesson(List<Long> cartIdList, Long memberId);
    List<LessonEnrollResponse> findEnrollLessonList(Long memberId);
    List<TutorLessonResponse> findTutorLessonList(Long memberId);
}
