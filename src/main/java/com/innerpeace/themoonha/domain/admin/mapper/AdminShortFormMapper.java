package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.ShortFormRegisterAdminRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminShortFormMapper {
    int insertShortForm(@Param("registerRequest") ShortFormRegisterAdminRequest registerAdminRequest,
                        @Param("shortFormVideoS3Url") String shortFormVideoS3Url);
}
