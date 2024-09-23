package com.innerpeace.themoonha.domain.admin.controller;

import com.innerpeace.themoonha.domain.admin.dto.LessonDetailAdminResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
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
 * 2024.09.09  	최유경        강좌 상세 조회
 * </pre>
 */
@RestController
@RequestMapping("/admin/lesson")
@Slf4j
@RequiredArgsConstructor
public class AdminLessonController {
    private final AdminLessonService adminLessonService;

    /**
     * 강좌 등록
     *
     * @param registerRequest 강좌 상세 내용
     * @param thumbnailFile 썸네일 사진
     * @param previewVideoFile 프롤로그 사진
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<CommonResponse> LessonAdd(@RequestPart("registerRequest") LessonRegisterAdminRequest registerRequest,
                                                    @RequestPart(value="thumbnailFile", required=false) MultipartFile thumbnailFile,
                                                    @RequestPart(value="previewVideoFile", required=false) MultipartFile previewVideoFile){
        log.info("/admin/lesson/register : {}", registerRequest.toString());
        adminLessonService.addLesson(registerRequest, thumbnailFile, previewVideoFile);

        return ResponseEntity.ok(CommonResponse.from(SuccessCode.ADMIN_LESSON_REGISTER_SUCCESS.getMessage()));
    }

    /**
     * 강좌 리스트 조회
     *
     * @param lessonListAdminRequest
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<LessonDetailAdminResponse>> LessonList(LessonListAdminRequest lessonListAdminRequest){
        log.info("LessonList : {}", lessonListAdminRequest.toString());
        List<LessonDetailAdminResponse> lessonDTOList = adminLessonService.findLessonList(lessonListAdminRequest);
        return ResponseEntity.ok(lessonDTOList);
    }

    /**
     * 강좌 상세 조회
     *
     * @param lessonId
     * @return
     */
    @GetMapping("/detail/{lessonId}")
    public ResponseEntity<LessonDetailAdminResponse> LessonDetail(@PathVariable("lessonId") Long lessonId){
        log.info("LessonDetail : {}", lessonId);
        LessonDetailAdminResponse lessonDetailAdminResponse = adminLessonService.findLesson(lessonId);
        return ResponseEntity.ok(lessonDetailAdminResponse);
    }



}
