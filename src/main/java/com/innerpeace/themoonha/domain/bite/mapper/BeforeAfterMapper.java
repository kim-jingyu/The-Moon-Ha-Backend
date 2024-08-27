package com.innerpeace.themoonha.domain.bite.mapper;

import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterDTO;
import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterResponse;
import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterSearchResponse;

import java.util.List;

/**
 * 비포애프터 매퍼
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  김진규        최초 생성
 * </pre>
 * @since 2024.08.27
 */
public interface BeforeAfterMapper {
    List<BeforeAfterResponse> findBeforeAfterList();
    Long makeBeforeAfter(BeforeAfterDTO beforeAfterDTO);
    List<BeforeAfterSearchResponse> findBeforeAfterListByTitle(String keyword);
}
