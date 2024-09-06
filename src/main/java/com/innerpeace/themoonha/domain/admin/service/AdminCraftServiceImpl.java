package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.PrologueListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.dto.PrologueThemeListAdminResponse;
import com.innerpeace.themoonha.domain.admin.mapper.AdminCraftMapper;
import com.innerpeace.themoonha.domain.craft.dto.SuggestionDTO;
import com.innerpeace.themoonha.domain.craft.mapper.CraftMapper;
import com.innerpeace.themoonha.global.service.S3Service;
import com.innerpeace.themoonha.global.util.Criteria;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 어드민 문화공방 관리 서비스 구현체
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
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminCraftServiceImpl implements AdminCraftService{
    private final AdminCraftMapper adminCraftMapper;
    private final CraftMapper craftMapper;
    private final S3Service s3Service;

    /**
     * 테마 및 프롤로그 등록
     *
     * @param prologueRegisterAdminRequest 테마 정보
     * @param thumbnailFile 썸네일 사진 리스트
     * @param prologueVideoFil 영상 파일 리스트
     */
    @Override
    @Transactional
    public void addPrologue(Long memberId,
                            PrologueRegisterAdminRequest prologueRegisterAdminRequest,
                            List<MultipartFile> thumbnailFile,
                            List<MultipartFile> prologueVideoFil) {

        // S3에 업로드
        List<String> thumbnailS3Url = s3Service.saveFiles(thumbnailFile, "craft");
        List<String> prologueS3Url = s3Service.saveFiles(prologueVideoFil, "craft");

        // 데이터베이스 저장
        adminCraftMapper.insertPrologue(
                memberId,
                prologueRegisterAdminRequest,
                thumbnailS3Url,
                prologueS3Url);
    }

    /**
     * 테마 조회
     *
     * @return 테마 리스트
     */
    @Override
    public List<PrologueThemeListAdminResponse> findPrologueThemeList() {
        return adminCraftMapper.selectPrologueThemeList();
    }


    /**
     * 테마별 프롤로그 조회
     * @param prologueThemeId 테마 ID
     * @return 테마별 프롤로그 리스트
     */
    @Override
    public List<PrologueListAdminResponse> findPrologueList(Long prologueThemeId) {
        return adminCraftMapper.selectPrologueListByTheme(prologueThemeId);
    }

    /**
     * 제안합니다 조회
     *
     * @param criteria 페이징 처리
     * @return 제안합니다 리스트
     */
    @Override
    public List<SuggestionDTO> findSuggestionList(Criteria criteria) {
        return craftMapper.selectSuggestionList(criteria);
    }
}
