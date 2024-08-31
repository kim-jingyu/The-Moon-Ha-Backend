package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminCraftMapper {

    void insertPrologue(@Param("registerRequest")PrologueRegisterAdminRequest registerAdminRequest,
                        @Param("thumbnailS3Url") List<String> thumbnailS3Url,
                        @Param("prologueS3Url") List<String> prologueS3Url);

    void selectPrologueList();

}
