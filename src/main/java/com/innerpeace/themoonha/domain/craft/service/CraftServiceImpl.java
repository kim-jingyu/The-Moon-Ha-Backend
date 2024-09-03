package com.innerpeace.themoonha.domain.craft.service;

import com.innerpeace.themoonha.domain.craft.dto.*;
import com.innerpeace.themoonha.domain.craft.mapper.CraftMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.util.Criteria;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 문화공방 서비스 구현체
 * @author 손승완
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	손승완       최초 생성
 * 2024.08.26   손승완       프롤로그 상세 조회 구현
 * 2024.08.27   손승완       제안합니다 댓글 작성 기능 추가
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CraftServiceImpl implements CraftService {
    private final CraftMapper craftMapper;

    @Override
    @Transactional(readOnly = true)
    public CraftMainResponse findCraftMain(Criteria criteria) {
        List<PrologueDTO> prologueList = craftMapper.selectPrologueList();
        List<WishLessonDTO> wishLessonList = craftMapper.selectWishLessonList();
        List<SuggestionDTO> suggestionList = craftMapper.selectSuggestionList(criteria);

        return CraftMainResponse.of(prologueList, wishLessonList, suggestionList);
    }

    @Override
    @Transactional
    public CommonResponse addSuggestion(SuggestionRequest suggestionRequest, Long memberId) {
        if (craftMapper.insertSuggestion(suggestionRequest, memberId) != 1) {
            throw new CustomException(ErrorCode.SUGGESTION_FAILED);
        }
        return CommonResponse.from(SuccessCode.SUGGESTION_WRITE_SUCCESS.getMessage());
    }

    @Override
    @Transactional
    public CommonResponse addPrologueLike(Long prologueId, Long memberId) {
        if (craftMapper.insertPrologueLike(prologueId, memberId) != 1) {
            throw new CustomException(ErrorCode.PROLOGUE_LIKE_ALREADY_EXISTS);
        }

        return CommonResponse.from(SuccessCode.PROLOGUE_LIKE_SUCCESS.getMessage());
    }

    @Override
    public CommonResponse addWishLessonVote(Long wishLessonId, Long memberId) {
        if (craftMapper.insertWishLessonVote(wishLessonId, memberId) != 1) {
            throw new CustomException(ErrorCode.WISHLESSON_VOTE_ALREADY_EXISTS);
        }

        return CommonResponse.from(SuccessCode.WISHLESSON_VOTE_SUCCESS.getMessage());
    }
}
