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

    void addLesson(LessonRegisterAdminRequest registerRequest, MultipartFile thumbnailFile, MultipartFile previewVideoFile);
    List<LessonDetailAdminResponse> findLessonList(LessonListAdminRequest lessonListAdminRequest);

    LessonDetailAdminResponse findLesson(Long lessonId);
}
