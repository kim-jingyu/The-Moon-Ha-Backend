package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.PrologueListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 어드민 문화공방 관리 서비스
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
public interface AdminCraftService {

    void addPrologue(PrologueRegisterAdminRequest prologueRegisterAdminRequest,
                     List<MultipartFile> thumbnailFile,
                     List<MultipartFile> prologueVideoFil);

    List<PrologueListAdminResponse> findPrologueList();

}
