package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.LessonDetailAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonListAdminRequest;
import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.mapper.AdminLessonMapper;
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
 * 어드민 강좌 관리 서비스 구현체
 * @author 최유경
 * @since 2024.08.28
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.28  	최유경       최초 생성
 * 2024.08.28   최유경       강좌 조희 기능
 * 2024.09.09  	최유경        강좌 상세 조회
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminLessonServiceImpl implements AdminLessonService {
    private final AdminLessonMapper adminLessonMapper;
    private final S3Service s3Service;

    /**
     * 강좌 등록 메서드
     *
     * @param registerRequest 강좌 등록 요청 dto
     * @param thumbnailFile 강좌 썸네일 사진 파일
     * @param previewVideoFile 강좌 프리뷰 영상 파일
     */
    @Override
    @Transactional
    public void addLesson(LessonRegisterAdminRequest registerRequest, MultipartFile thumbnailFile, MultipartFile previewVideoFile) {
        // 중복 여부 확인
        // 1. 동일한 강사가 동일 지점, 동일 시간대에 중복된 강좌 확인
        // 2. 현재 진행중인 강좌 중, 중복된 강좌명 확인
        if( adminLessonMapper.selectLessonByTutorSchedule(registerRequest) != 0 )
            throw new CustomException(ErrorCode.ADMIN_LESSON_REGISTER_DUPLICATE);

        // 썸네일 및 프리뷰 S3 업로드
        String thumbnailS3Url = null;
        String previewS3Url = null;
        try{
            if(thumbnailFile!=null && !thumbnailFile.isEmpty())
                thumbnailS3Url = s3Service.saveFile(thumbnailFile, "lesson/image");
            if(previewVideoFile!=null && !previewVideoFile.isEmpty())
                previewS3Url = s3Service.saveFile(previewVideoFile, "lesson/preview");
        } catch (IOException e){
            throw  new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        // 데이터베이스에 저장하기
        if(adminLessonMapper.insertLesson(registerRequest, thumbnailS3Url, previewS3Url)!=1)
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }


    /**
     * 강좌 조회 메서드
     *
     * @param lessonListAdminRequest 조회 필터 요청 dto
     * @return 조회 결과 dto
     */
    @Override
    public List<LessonDetailAdminResponse> findLessonList(LessonListAdminRequest lessonListAdminRequest) {
        return adminLessonMapper.selectLessonList(lessonListAdminRequest);
    }

    /**
     * 강좌 상세 조회 메서드
     *
     * @param lessonId 강좌 조회 아이디
     * @return 상세 조회 dto
     */
    @Override
    public LessonDetailAdminResponse findLesson(Long lessonId) {
        return adminLessonMapper.selectLessonDetail(lessonId);
    }
}
