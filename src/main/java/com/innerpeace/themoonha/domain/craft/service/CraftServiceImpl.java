package com.innerpeace.themoonha.domain.craft.service;

import com.innerpeace.themoonha.domain.craft.dto.*;
import com.innerpeace.themoonha.domain.craft.mapper.CraftMapper;
import com.innerpeace.themoonha.global.util.Criteria;
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
        // 1. 프롤로그 목록 불러오기
        List<PrologueDTO> prologueList = craftMapper.selectPrologueList();

        // 2. 듣고싶은 강좌 목록 불러오기
        List<WishLessonDTO> wishLessonList = craftMapper.selectWishLessonList();

        // 3. 제안합니다 댓글 목록 불러오기
        List<SuggestionDTO> suggestionList = craftMapper.selectSuggestionList(criteria);

        return CraftMainResponse.of(prologueList, wishLessonList, suggestionList);
    }

    @Override
    public PrologueDetailResponse findPrologueDetail(Long prologueId) {
        Long memberId = 1L;
        PrologueDetailResponse prologueDetailResponse = craftMapper.selectPrologueDetail(prologueId, memberId);
        log.info("response = {}", prologueDetailResponse);
        return prologueDetailResponse;
    }
}
