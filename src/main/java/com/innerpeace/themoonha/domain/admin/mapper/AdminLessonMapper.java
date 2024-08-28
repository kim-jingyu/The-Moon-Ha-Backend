package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.AdminLessonListRequest;
import com.innerpeace.themoonha.domain.admin.dto.AdminLessonResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterRequest;
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
    int selectLessonByTutorSchedule(LessonRegisterRequest lessonRegisterRequest);
    int insertLesson(@Param("registerRequest") LessonRegisterRequest registerRequest,
                     @Param("thumbnailS3Url") String thumbnailS3Url,
                     @Param("previewS3Url") String previewS3Url);

    List<AdminLessonResponse> selectLessonList(AdminLessonListRequest lessonListRequest);

}
