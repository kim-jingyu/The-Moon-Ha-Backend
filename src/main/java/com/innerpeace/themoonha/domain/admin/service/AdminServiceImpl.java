package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.AdminDataResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonBranchDTO;
import com.innerpeace.themoonha.domain.admin.mapper.AdminMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 어드민 초기 데이터 조회 서비스 구현체
 * @author 최유경
 * @since 2024.09.05
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.05  	최유경       최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    /**
     * 초기 데이터 조회
     * @return
     */
    @Override
    public AdminDataResponse findData() {
        List<LessonBranchDTO> lessonBranchDTOList = adminMapper.selectLessonAndBranchList();
        return AdminDataResponse.from(lessonBranchDTOList);
    }
}
