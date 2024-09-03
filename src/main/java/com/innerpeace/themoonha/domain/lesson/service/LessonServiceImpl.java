package com.innerpeace.themoonha.domain.lesson.service;

import com.innerpeace.themoonha.domain.lesson.dto.*;
import com.innerpeace.themoonha.domain.lesson.mapper.LessonMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 강좌 서비스 구현체
 *
 * @author 손승완
 * @since 2024.08.24
 * @version 1.0
 * @since 2024.08.24
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
@Service
@RequiredArgsConstructor
@Slf4j
public class LessonServiceImpl implements LessonService {
    private final LessonMapper lessonMapper;

    @Override
    @Transactional(readOnly = true)
    public LessonListResponse findLessonList(LessonListRequest lessonListRequest) {
        String memberName = "고객1"; // 임시값

        List<LessonDTO> lessonList = lessonMapper.selectLessonList(lessonListRequest);
        List<ShortFormDTO> shortFormList = lessonMapper.selectShortFormList();
        return LessonListResponse.of(lessonList, shortFormList, memberName, lessonListRequest.getBranchId());
    }

    @Override
    public LessonDetailResponse findLessonDetail(Long lessonId) {
        return lessonMapper.selectLessonDetail(lessonId)
                .orElseThrow(() -> new CustomException(ErrorCode.LESSON_NOT_FOUND));
    }

    @Override
    public TutorDetailResponse findTutorDetail(Long tutorId) {
        List<TutorLessonDetailDTO> tutorDetailList = Optional
                .ofNullable(lessonMapper.selectTutorDetail(tutorId))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CustomException(ErrorCode.TUTOR_NOT_FOUND));

        return TutorDetailResponse.from(tutorDetailList);
    }

    @Override
    public List<CartResponse> findCartList(Long memberId) {
        return lessonMapper.selectCartList(memberId);
    }

    @Override
    @Transactional
    public CommonResponse addCart(CartRequest cartRequest) {
        if (lessonMapper.insertCart(cartRequest) == 1) {
            return CommonResponse.of(true, SuccessCode.CART_LESSON_ADDED_SUCCESS.getMessage());
        }

        throw new CustomException(ErrorCode.CART_LESSON_ALREADY_EXISTS);
    }

    @Override
    @Transactional
    public CommonResponse payLesson(List<Long> cartIdList, Long memberId) {
        if (lessonMapper.insertSugang(cartIdList, memberId) != cartIdList.size()) {
            throw new CustomException(ErrorCode.SUGANG_FAILED);
        }

        if (lessonMapper.deleteCart(cartIdList, memberId) != cartIdList.size()) {
            throw new CustomException(ErrorCode.SUGANG_FAILED);
        }

        return CommonResponse.of(true, SuccessCode.SUGANG_APPLICATION_SUCCESS.getMessage());
    }

}
