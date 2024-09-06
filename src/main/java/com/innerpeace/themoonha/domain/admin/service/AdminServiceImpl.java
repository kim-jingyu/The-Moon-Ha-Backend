package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.AdminDataResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonBranchDTO;
import com.innerpeace.themoonha.domain.admin.mapper.AdminMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    @Override
    public AdminDataResponse findData() {
        List<LessonBranchDTO> lessonBranchDTOList = adminMapper.selectLessonAndBranchList();
        return AdminDataResponse.from(lessonBranchDTOList);
    }
}
