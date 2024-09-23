package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.LessonDetailAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonListAdminRequest;
import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterAdminRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 어드민 강좌 관리 서비스
 * @author 최유경
 * @since 2024.08.28
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.28  	최유경       최초 생성
 * 2024.08.28   최유경       강좌 조희 기능
 * 2024.09.09  	최유경       강좌 상세 조회
 * </pre>
 */
public interface AdminLessonService {

    /**
     * 강좌 등록 메서드
     *
     * @param registerRequest 강좌 등록 요청 dto
     * @param thumbnailFile 강좌 썸네일 사진 파일
     * @param previewVideoFile 강좌 프리뷰 영상 파일
     */
    void addLesson(LessonRegisterAdminRequest registerRequest, MultipartFile thumbnailFile, MultipartFile previewVideoFile);

    /**
     * 강좌 조회 메서드
     *
     * @param lessonListAdminRequest 조회 필터 요청 dto
     * @return 조회 결과 dto
     */
    List<LessonDetailAdminResponse> findLessonList(LessonListAdminRequest lessonListAdminRequest);

    /**
     * 강좌 상세 조회 메서드
     *
     * @param lessonId 강좌 조회 아이디
     * @return 상세 조회 dto
     */
    LessonDetailAdminResponse findLesson(Long lessonId);
}
