package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.AdminLessonResponse;
import com.innerpeace.themoonha.domain.admin.dto.AdminLessonListRequest;
import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterRequest;
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
 * </pre>
 */
public interface AdminLessonService {

    void addLesson(LessonRegisterRequest registerRequest, MultipartFile thumbnailFile, MultipartFile previewVideoFile);
    List<AdminLessonResponse> findLessonList(AdminLessonListRequest lessonListRequest);
}
