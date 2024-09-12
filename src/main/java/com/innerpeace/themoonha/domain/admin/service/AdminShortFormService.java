package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.ShortFormListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.ShortFormRegisterAdminRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 어드민 숏폼 관리 서비스
 * @author 최유경
 * @since 2024.08.29
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.29  	최유경       최초 생성
 * 2024.08.30   최유경       숏폼 조회
 * 2024.09.05   최유경       숏폼 썸네일 등록
 * </pre>
 */
public interface AdminShortFormService {
    void addShortForm(ShortFormRegisterAdminRequest shortFormRegisterAdminRequest, MultipartFile thumbnailFile,  MultipartFile shortFormVideoFile);

    List<ShortFormListAdminResponse> findShortFormList(Long branchId, String yearMonth);
}
