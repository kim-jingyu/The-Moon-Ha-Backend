package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.LessonListAdminRequest;
import com.innerpeace.themoonha.domain.admin.dto.LessonAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterAdminRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 어드민 강좌 관리 매퍼
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
@Mapper
public interface AdminLessonMapper {
    int selectLessonByTutorSchedule(LessonRegisterAdminRequest lessonRegisterAdminRequest);
    int insertLesson(@Param("registerRequest") LessonRegisterAdminRequest registerRequest,
                     @Param("thumbnailS3Url") String thumbnailS3Url,
                     @Param("previewS3Url") String previewS3Url);

    List<LessonAdminResponse> selectLessonList(LessonListAdminRequest lessonListAdminRequest);

}
