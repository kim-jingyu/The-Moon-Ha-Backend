package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.PrologueListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.mapper.AdminCraftMapper;
import com.innerpeace.themoonha.global.service.S3Service;
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
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminCraftServiceImpl implements AdminCraftService{
    private final AdminCraftMapper adminCraftMapper;
    private final S3Service s3Service;

    @Override
    @Transactional
    public void addPrologue(PrologueRegisterAdminRequest prologueRegisterAdminRequest,
                            List<MultipartFile> thumbnailFile,
                            List<MultipartFile> prologueVideoFil) {

        // S3에 업로드
        List<String> thumbnailS3Url = s3Service.saveFiles(thumbnailFile, "craft");
        List<String> prologueS3Url = s3Service.saveFiles(prologueVideoFil, "craft");

        // 데이터베이스 저장
        adminCraftMapper.insertPrologue(prologueRegisterAdminRequest,
                thumbnailS3Url,
                prologueS3Url);

    }

    @Override
    public List<PrologueListAdminResponse> findPrologueList() {
        return null;
    }
}
