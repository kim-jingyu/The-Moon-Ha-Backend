package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminCraftMapper {

    void insertPrologue(@Param("registerRequest")PrologueRegisterAdminRequest registerAdminRequest,
                        @Param("thumbnailS3Url") String thumbnailS3Url,
                        @Param("prologueS3Url") String prologueS3Url);

    void selectPrologueList();

}
