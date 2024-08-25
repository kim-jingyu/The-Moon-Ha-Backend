package com.innerpeace.themoonha.domain.craft.service;

import com.innerpeace.themoonha.domain.craft.dto.CraftMainResponse;
import com.innerpeace.themoonha.global.util.Criteria;

/**
 * 문화공방 서비스 인터페이스
 * @author 손승완
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	손승완       최초 생성
 * </pre>
 */
public interface CraftService {
    CraftMainResponse findCraftMain(Criteria criteria);
}
