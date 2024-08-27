package com.innerpeace.themoonha.domain.bite.service;

import com.innerpeace.themoonha.domain.bite.dto.*;
import com.innerpeace.themoonha.domain.bite.mapper.BeforeAfterMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 비포애프터 서비스 구현체
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  김진규        최초 생성
 * </pre>
 * @since 2024.08.27
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeforeAfterServiceImpl implements BeforeAfterService {
    private final BeforeAfterMapper beforeAfterMapper;
    private final S3Service s3Service;

    @Override
    public List<BeforeAfterResponse> getBeforeAfterList() {
        return beforeAfterMapper.findBeforeAfterList();
    }

    @Override
    @Transactional
    public CommonResponse makeBeforeAfter(Long memberId, BeforeAfterRequest beforeAfterRequest, MultipartFile beforeContent, MultipartFile afterContent) throws IOException {
        BeforeAfterDTO beforeAfterDTO = BeforeAfterDTO.of(memberId, beforeAfterRequest, s3Service.saveFile(beforeContent), s3Service.saveFile(afterContent));
        beforeAfterMapper.insertBeforeAfter(beforeAfterDTO);
        return CommonResponse.from(String.valueOf(beforeAfterDTO.getBaId()));
    }

    @Override
    public List<BeforeAfterSearchResponse> findBeforeAfterByTitle(String keyword) {
        return beforeAfterMapper.findBeforeAfterListByTitle(keyword);
    }

    @Override
    public List<BeforeAfterSearchResponse> findBeforeAfterByHashTags(List<String> hashtags) {
        return null;
    }
}
