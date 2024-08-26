package com.innerpeace.themoonha.domain.lounge.service;

import com.innerpeace.themoonha.domain.lounge.dto.LoungeListResponse;
import com.innerpeace.themoonha.domain.lounge.mapper.LoungeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 라운지 서비스 구현체
 * @author 조희정
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	조희정       최초 생성
 * 2024.08.25  	조희정       findLoungeList 메서드 추가
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoungeServiceImpl implements LoungeService {

    private final LoungeMapper loungeMapper;

    /**
     * 라운지 목록 조회
     * @param memberId
     * @return
     */
    @Override
    public List<LoungeListResponse> findLoungeList(Long memberId) {
        return loungeMapper.selectLoungeList(memberId);
    }
}
