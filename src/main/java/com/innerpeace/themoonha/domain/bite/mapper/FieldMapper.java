package com.innerpeace.themoonha.domain.bite.mapper;

import com.innerpeace.themoonha.domain.bite.dto.field.FieldDTO;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldResponseForDetail;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldResponseForList;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldSearchResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<FieldResponseForList> findFieldList();
    void insertField(FieldDTO fieldDTO);
    List<FieldSearchResponse> findBeforeAfterListByTitle(String keyword);
    void insertHashtagAndFieldHashtag(@Param("hashtags") List<String> hashtags, @Param("fieldId") Long fieldId);
    List<FieldSearchResponse> findFieldListByHashtags(@Param("hashtags") List<String> hashtags);
    FieldResponseForDetail findFieldContent(Long fieldId);
}
