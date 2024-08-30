package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.ShortFormRegisterAdminRequest;
import org.springframework.web.multipart.MultipartFile;

public interface AdminShortFormService {
    void addShortForm(ShortFormRegisterAdminRequest shortFormRegisterAdminRequest, MultipartFile shortFormVideoFile);
}
