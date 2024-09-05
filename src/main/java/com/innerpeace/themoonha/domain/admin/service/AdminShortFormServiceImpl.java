package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.ShortFormListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.ShortFormRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.mapper.AdminLessonMapper;
import com.innerpeace.themoonha.domain.admin.mapper.AdminShortFormMapper;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.service.S3Service;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 어드민 숏폼 관리 서비스 구현체
 * @author 최유경
 * @since 2024.08.29
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.29  	최유경       최초 생성
 * 2024.08.30   최유경       숏폼 조회
 * 2024.09.05   최유경       숏폼 썸네일 등록
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminShortFormServiceImpl implements AdminShortFormService {
    private final AdminShortFormMapper adminShortFormMapper;
    private final S3Service s3Service;

    @Override
    @Transactional
    public void addShortForm(ShortFormRegisterAdminRequest shortFormRegisterAdminRequest, MultipartFile thumbnailFile, MultipartFile shortFormVideoFile) {
        try{
            // S3에 업로드
            String thumbnailS3Url = null;
            String shortFormVideoS3Url = null;
            if(thumbnailFile!=null  && !thumbnailFile.isEmpty())
                thumbnailS3Url = s3Service.saveFile(thumbnailFile, "shortform");

            if(shortFormVideoFile!=null && !shortFormVideoFile.isEmpty())
                shortFormVideoS3Url = s3Service.saveFile(shortFormVideoFile, "shortform");
            log.info("thumbnailS3Url : ", thumbnailS3Url);
            log.info("shortFormVideoS3Url : ", shortFormVideoS3Url);
            // 데이터베이스 저장
            if(adminShortFormMapper.insertShortForm(shortFormRegisterAdminRequest, thumbnailS3Url, shortFormVideoS3Url)!=1)
                throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);

        } catch (IOException e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ShortFormListAdminResponse> findShortFormList(Long branchId, int expiredYn) {
        return adminShortFormMapper.selectShortFormList(branchId, expiredYn);
    }
}
