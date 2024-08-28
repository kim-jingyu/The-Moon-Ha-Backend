package com.innerpeace.themoonha.domain.bite.mapper;

import com.innerpeace.themoonha.domain.bite.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 비포애프터 매퍼
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  김진규       최초 생성
 * 2024.08.27  김진규       findBeforeAfterList, insertBeforeAfter, findBeforeAfterListByTitle 메서드 추가
 * 2024.08.28  김진규       insertHashtag, insertBeforeAfterHashtag, findBeforeAfterListByHashtags 메서드 추가
 * </pre>
 * @since 2024.08.27
 */
public interface BeforeAfterMapper {
    List<BeforeAfterResponse> findBeforeAfterList();
    void insertBeforeAfter(BeforeAfterDTO beforeAfterDTO);
    List<BeforeAfterSearchResponse> findBeforeAfterListByTitle(String keyword);
    void insertHashtag(Map<String, Object> params);
    void insertBeforeAfterHashtag(@Param("beforeAfterId") Long beforeAfterId, @Param("hashtagId") Long hashtagId);
    List<BeforeAfterSearchResponse> findBeforeAfterListByHashtags(@Param("hashtags") List<String> hashtags);
    Optional<Hashtag> findHashtag(@Param("name") String name);
}
