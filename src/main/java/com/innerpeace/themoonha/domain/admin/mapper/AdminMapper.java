package com.innerpeace.themoonha.domain.admin.mapper;

import com.innerpeace.themoonha.domain.admin.dto.LessonBranchDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    List<LessonBranchDTO> selectLessonAndBranchList();
}
