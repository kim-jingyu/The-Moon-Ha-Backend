package com.innerpeace.themoonha.domain.bite.service.field;

import com.innerpeace.themoonha.domain.bite.dto.field.FieldRequest;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldDetailResponse;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldListResponse;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldSearchResponse;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 분야별 한 입 서비스 인터페이스
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  김진규        최초 생성
 * 2024.08.31  김진규        getFieldList, makeField, findFieldByTitle, findFieldByHashTags, getFieldContent 메서드 추가
 * </pre>
 * @since 2024.08.31
 */
public interface FieldService {
    List<FieldListResponse> getFieldList();
    FieldDetailResponse getFieldContent(Long beforeAfterId);
    CommonResponse makeField(Long memberId, FieldRequest fieldRequest, MultipartFile thumbnail, MultipartFile content);
    List<FieldSearchResponse> findFieldByTitle(String keyword);
    List<FieldSearchResponse> findFieldByHashTags(List<String> hashtags);
    List<FieldListResponse> getFieldListOrderByTitle();
    List<FieldDetailResponse> getFieldContentsByLatest();
    List<FieldDetailResponse> getFieldContentsByTitle();
}
