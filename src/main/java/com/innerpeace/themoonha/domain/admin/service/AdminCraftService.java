package com.innerpeace.themoonha.domain.admin.service;

import com.innerpeace.themoonha.domain.admin.dto.PrologueListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AdminCraftService {

    void addPrologue(PrologueRegisterAdminRequest prologueRegisterAdminRequest,
                     MultipartFile thumbnailFile,
                     MultipartFile prologueVideoFil);

    List<PrologueListAdminResponse> findPrologueList();

}
