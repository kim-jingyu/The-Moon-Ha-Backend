package com.innerpeace.themoonha.domain.bite.mapper;

import com.innerpeace.themoonha.domain.bite.dto.field.FieldDTO;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldDetailResponse;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldListResponse;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldSearchResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 분야별 한 입 매퍼
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  김진규       최초 생성
 * 2024.08.31  김진규       findFieldList, insertField, findBeforeAfterListByTitle, insertHashtagAndFieldHashtag, findFieldListByHashtags, findFieldContent 메서드 추가
 * </pre>
 * @since 2024.08.31
 */
public interface FieldMapper {
    List<FieldListResponse> findFieldList();
    List<FieldListResponse> findFieldListOrderByTitle();
    void insertField(FieldDTO fieldDTO);
    List<FieldSearchResponse> findFieldListByTitle(String keyword);
    void insertHashtagAndFieldHashtag(@Param("hashtags") List<String> hashtags, @Param("fieldId") Long fieldId);
    List<FieldSearchResponse> findFieldListByHashtags(@Param("hashtags") List<String> hashtags);
    Optional<FieldDetailResponse> findFieldDetail(Long fieldId);
    List<FieldDetailResponse> findFieldDetailsByLatest();
    List<FieldDetailResponse> findFieldDetailsByTitle();
}
