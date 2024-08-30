package com.innerpeace.themoonha.domain.admin.controller;

import com.innerpeace.themoonha.domain.admin.dto.LessonAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonListAdminRequest;
import com.innerpeace.themoonha.domain.admin.dto.ShortFormListAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.ShortFormRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.service.AdminShortFormService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 어드민 숏폼 관리 컨트롤러
 * @author 최유경
 * @since 2024.08.29
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.29  	최유경       최초 생성
 * 2024.08.30   최유경       어드민 숏폼 조회
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/shortform")
@Slf4j
public class AdminShortFormController {
    private final AdminShortFormService adminShortFormService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> ShortFormAdd(@RequestPart("registerRequest") ShortFormRegisterAdminRequest registerRequest,
                                                    @RequestPart(value="shortFormVideoFile", required=false) MultipartFile shortFormVideoFile){
        log.info("/admin/shortform/register : {}", registerRequest.toString());
        adminShortFormService.addShortForm(registerRequest, shortFormVideoFile);

        return ResponseEntity.ok(CommonResponse.from(SuccessCode.ADMIN_SHORTFROM_REGISTER_SUCCESS.getMessage()));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ShortFormListAdminResponse>> ShortFormList(@RequestParam(value = "branchId", required = false) Long branchId,
                                                                          @RequestParam(value = "expiredYn") int expiredYn){
        log.info("/admin/shortform/list  : {}, {} ", branchId, expiredYn);
        List<ShortFormListAdminResponse> shortFormList =  adminShortFormService.findShortFormList(branchId, expiredYn);
        return ResponseEntity.ok(shortFormList);
    }

}
