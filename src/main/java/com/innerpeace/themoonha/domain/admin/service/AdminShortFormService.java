package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.ShortFormListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.ShortFormRegisterAdminRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AdminShortFormService {
    void addShortForm(ShortFormRegisterAdminRequest shortFormRegisterAdminRequest, MultipartFile shortFormVideoFile);

    List<ShortFormListAdminResponse> findShortFormList(Long branchId, int expiredYn);
}
