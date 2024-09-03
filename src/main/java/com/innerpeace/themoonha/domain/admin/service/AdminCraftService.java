package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.PrologueListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.dto.PrologueThemeListAdminResponse;
import com.innerpeace.themoonha.domain.craft.dto.SuggestionDTO;
import com.innerpeace.themoonha.global.util.Criteria;
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
 * 2024.09.01   최유경       제안합니다 조회
 * </pre>
 */
public interface AdminCraftService {

    /**
     * 테마 및 프롤로그 등록
     *
     * @param prologueRegisterAdminRequest 테마 정보
     * @param thumbnailFile 썸네일 사진 리스트
     * @param prologueVideoFil 영상 파일 리스트
     */
    void addPrologue(PrologueRegisterAdminRequest prologueRegisterAdminRequest,
                     List<MultipartFile> thumbnailFile,
                     List<MultipartFile> prologueVideoFil);

    /**
     * 테마 조회
     *
     * @return 테마 리스트
     */
    List<PrologueThemeListAdminResponse> findPrologueThemeList();

    /**
     * 테마별 프롤로그 조회
     * @param prologueThemeId 테마 ID
     * @return 테마별 프롤로그 리스트
     */
    List<PrologueListAdminResponse> findPrologueList(Long prologueThemeId);

    /**
     * 제안합니다 조회
     *
     * @param criteria 페이징 처리
     * @return 제안합니다 리스트
     */
    List<SuggestionDTO> findSuggestionList(Criteria criteria);
}
