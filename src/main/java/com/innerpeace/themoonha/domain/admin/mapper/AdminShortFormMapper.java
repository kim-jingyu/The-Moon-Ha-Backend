package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.ShortFormListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.ShortFormRegisterAdminRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 어드민 숏폼 관리 매퍼
 * @author 최유경
 * @since 2024.08.29
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.29  	최유경       최초 생성
 * 2024.08.30   최유경       숏폼 조회
 * </pre>
 */
@Mapper
public interface AdminShortFormMapper {
    int insertShortForm(@Param("registerRequest") ShortFormRegisterAdminRequest registerAdminRequest,
                        @Param("shortFormVideoS3Url") String shortFormVideoS3Url);

    List<ShortFormListAdminResponse> selectShortFormList(@Param("branchId") Long branchId,
                                                         @Param("expiredYn") int expiredYn);
}
