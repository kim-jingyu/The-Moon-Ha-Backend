package com.innerpeace.themoonha.domain.admin.controller;

import com.innerpeace.themoonha.domain.admin.dto.LessonAdminResponse;
import com.innerpeace.themoonha.domain.admin.dto.LessonListAdminRequest;
import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterAdminRequest;
import com.innerpeace.themoonha.domain.admin.service.AdminLessonService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 어드민 강좌 관리 컨트롤러
 * @author 최유경
 * @since 2024.08.28
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.28  	최유경       최초 생성
 * 2024.08.28   최유경       강좌 조희 기능
 * </pre>
 */
@RestController
@RequestMapping("/admin/lesson")
@Slf4j
@RequiredArgsConstructor
public class AdminLessonController {
    private final AdminLessonService adminLessonService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> LessonAdd(@RequestPart("registerRequest") LessonRegisterAdminRequest registerRequest,
                                                    @RequestPart(value="thumbnailFile", required=false) MultipartFile thumbnailFile,
                                                    @RequestPart(value="previewVideoFile", required=false) MultipartFile previewVideoFile){
        log.info("/admin/lesson/register : {}", registerRequest.toString());
        adminLessonService.addLesson(registerRequest, thumbnailFile, previewVideoFile);

        return ResponseEntity.ok(CommonResponse.from(SuccessCode.ADMIN_LESSON_REGISTER_SUCCESS.getMessage()));
    }

    @GetMapping("/list")
    public ResponseEntity<List<LessonAdminResponse>> LessonList(LessonListAdminRequest lessonListAdminRequest){
        log.info("LessonList : {}", lessonListAdminRequest.toString());
        List<LessonAdminResponse> lessonDTOList = adminLessonService.findLessonList(lessonListAdminRequest);
        return ResponseEntity.ok(lessonDTOList);
    }



}
