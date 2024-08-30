package com.innerpeace.themoonha.domain.admin.controller;

import com.innerpeace.themoonha.domain.admin.dto.PrologueRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.service.AdminCraftService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/craft")
@RequiredArgsConstructor
@Slf4j
public class AdminCraftController {
    private final AdminCraftService adminCraftService;

    @PostMapping("/prologue/register")
    public ResponseEntity<CommonResponse> PrologueAdd(@RequestPart("registerRequest") PrologueRegisterAdminRequest registerAdminRequest,
                                                      @RequestPart("thumbnailFile") MultipartFile thumbnailFile,
                                                      @RequestPart("prologueVideoFile")MultipartFile prologueVideoFile){
        adminCraftService.addPrologue(registerAdminRequest, thumbnailFile, prologueVideoFile);

        return ResponseEntity.ok(CommonResponse.from(SuccessCode.ADMIN_PROLOGUE_REGISTER_SUCCESS.getMessage()));
    }
}
