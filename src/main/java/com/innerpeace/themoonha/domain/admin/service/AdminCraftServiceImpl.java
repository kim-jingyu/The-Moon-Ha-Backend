package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.PrologueListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.mapper.AdminCraftMapper;
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
public class AdminCraftServiceImpl implements AdminCraftService{
    private final AdminCraftMapper adminCraftMapper;
    private final S3Service s3Service;

    @Override
    @Transactional
    public void addPrologue(PrologueRegisterAdminRequest prologueRegisterAdminRequest,
                            MultipartFile thumbnailFile,
                            MultipartFile prologueVideoFil) {
        try{
            // S3에 업로드
            String thumbnailS3Url = s3Service.saveFile(thumbnailFile, "craft");
            String prologueS3Url = s3Service.saveFile(prologueVideoFil, "craft");

            // 데이터베이스 저장
            adminCraftMapper.insertPrologue(prologueRegisterAdminRequest, thumbnailS3Url, prologueS3Url);
        } catch (IOException e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<PrologueListAdminResponse> findPrologueList() {
        return null;
    }
}
