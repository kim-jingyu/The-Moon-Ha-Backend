package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.AdminDataResponse;

/**
 * 어드민 초기 데이터 조회 서비스
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
public interface AdminService {
    /**
     * 초기 데이터 조회
     * @return
     */
    AdminDataResponse findData();
}
