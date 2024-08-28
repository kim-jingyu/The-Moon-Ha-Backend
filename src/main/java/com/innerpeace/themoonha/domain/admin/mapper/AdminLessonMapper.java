package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.AdminLessonListRequest;
import com.innerpeace.themoonha.domain.admin.dto.AdminLessonResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminLessonMapper {
    int selectLessonByTutorSchedule(LessonRegisterRequest lessonRegisterRequest);
    int insertLesson(@Param("registerRequest") LessonRegisterRequest registerRequest,
                     @Param("thumbnailS3Url") String thumbnailS3Url,
                     @Param("previewS3Url") String previewS3Url);

    List<AdminLessonResponse> selectLessonList(AdminLessonListRequest lessonListRequest);

}
