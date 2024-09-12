package com.innerpeace.themoonha.domain.lesson.service;

import com.innerpeace.themoonha.domain.auth.mapper.AuthMapper;
import com.innerpeace.themoonha.domain.lesson.dto.*;
import com.innerpeace.themoonha.domain.lesson.mapper.LessonMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.entity.Member;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.service.RedisViewService;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 강좌 서비스 구현체
 *
 * @author 손승완
 * @version 1.0
 * @since 2024.08.24
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
    private static final String SHORTFORM = "shortForm";
    private final LessonMapper lessonMapper;
    private final AuthMapper authMapper;
    private final RedisViewService redisViewService;
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    @Transactional(readOnly = true)
    public LessonListResponse findLessonList(LessonListRequest lessonListRequest, Long memberId) {
        Member member = authMapper.selectByMemberId(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        List<LessonDTO> lessonList = lessonMapper.selectLessonList(lessonListRequest);
        List<ShortFormDTO> shortFormList = lessonMapper.selectShortFormList(lessonListRequest.getBranchId());
        return LessonListResponse.of(lessonList, shortFormList, member.getUsername(), lessonListRequest.getBranchId());
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

    @Override
    public List<LessonEnrollResponse> findEnrollLessonList(Long memberId) {
        return lessonMapper.selectLessonEnrollList(memberId);
    }

    @Override
    public List<LessonEnrollResponse> findFieldEnrollLessonList(Long memberId) {
        return lessonMapper.selectLessonFieldEnrollList(memberId);
    }

    @Override
    public List<TutorLessonResponse> findTutorLessonList(Long memberId) {
        return lessonMapper.selectTutorLessonList(memberId);
    }

    @Override
    public void increaseShortFormViewCountCache(Long shortFormId, Long memberId) {
        redisViewService.incrementViewCount(memberId, shortFormId, SHORTFORM);
    }

    @Override
    @Transactional
    public void increaseShortFormViewCountDB(List<ShortFormViewCountDTO> viewCountList) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            int resultCnt = 0;
            for (ShortFormViewCountDTO viewCountDTO : viewCountList) {
                resultCnt += lessonMapper.updateShortFormViewCount(viewCountDTO);
            }

            if (resultCnt != viewCountList.size()) {
                throw new CustomException(ErrorCode.SHORTFORM_VIEW_COUNT_INCREASE_FAILED);
            }

            sqlSession.commit();
            log.info("배치 작업 완료: 업데이트된 레코드 수 = {}", resultCnt);

        } catch (Exception e) {
            log.error("배치 작업 중 오류 발생", e);
            throw e;
        }
    }
}
