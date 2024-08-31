package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.dto.PrologueThemeListAdminResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 어드민 문화공방 관리 매퍼
 * @author 최유경
 * @since 2024.08.30
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.30  	최유경       최초 생성
 * 2024.08.31   최유경       프롤로그 테마 기획 변경
 * </pre>
 */
@Mapper
public interface AdminCraftMapper {

    void insertPrologue(@Param("registerRequest")PrologueRegisterAdminRequest registerAdminRequest,
                        @Param("thumbnailS3Url") List<String> thumbnailS3Url,
                        @Param("prologueS3Url") List<String> prologueS3Url);

    List<PrologueThemeListAdminResponse> selectPrologueThemeList();

}
