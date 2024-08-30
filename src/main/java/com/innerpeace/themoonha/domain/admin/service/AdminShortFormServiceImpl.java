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

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminShortFormServiceImpl implements AdminShortFormService {
    private final AdminShortFormMapper adminShortFormMapper;
    private final S3Service s3Service;

    @Override
    @Transactional
    public void addShortForm(ShortFormRegisterAdminRequest shortFormRegisterAdminRequest, MultipartFile shortFormVideoFile) {
        try{
            // S3에 업로드
            String shortFormVideoS3Url = null;
            if(shortFormVideoFile!=null)
                shortFormVideoS3Url = s3Service.saveFile(shortFormVideoFile, "shortform");

            // 데이터베이스 저장
            if(adminShortFormMapper.insertShortForm(shortFormRegisterAdminRequest, shortFormVideoS3Url)!=1)
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
