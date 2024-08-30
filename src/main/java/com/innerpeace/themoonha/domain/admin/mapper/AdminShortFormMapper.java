package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.ShortFormListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.ShortFormRegisterAdminRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminShortFormMapper {
    int insertShortForm(@Param("registerRequest") ShortFormRegisterAdminRequest registerAdminRequest,
                        @Param("shortFormVideoS3Url") String shortFormVideoS3Url);

    List<ShortFormListAdminResponse> selectShortFormList(@Param("branchId") Long branchId,
                                                         @Param("expiredYn") int expiredYn);
}
