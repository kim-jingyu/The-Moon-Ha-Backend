package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterRequest;
import com.innerpeace.themoonha.domain.admin.mapper.AdminLessonMapper;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminLessonServiceImpl implements AdminLessonService {
    private final AdminLessonMapper adminLessonMapper;

    @Override
    @Transactional
    public void addLesson(LessonRegisterRequest registerRequest) {
        // 중복 여부 확인
        // 1. 동일한 강사가 동일 지점, 동일 시간대에 중복된 강좌 확인
        // 2. 현재 진행중인 강좌 중, 중복된 강좌명 확인
        if( adminLessonMapper.selectLessonByTutorSchedule(registerRequest) != 0 )
            throw new CustomException(ErrorCode.ADMIN_LESSON_REGISTER_DUPLICATE);

        // 썸네일 및 프리뷰 S3 업로드
        String thumbnailS3Url = null;
        String previewS3Url = null;

        // url 받아서 Lesson builder 구성


        // 데이터베이스에 저장하기
        if(adminLessonMapper.insertLesson(registerRequest, thumbnailS3Url, previewS3Url)!=1)
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
