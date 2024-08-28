package com.innerpeace.themoonha.domain.admin.controller;

import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterRequest;
import com.innerpeace.themoonha.domain.admin.service.AdminLessonService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * </pre>
 */
@RestController
@RequestMapping("/admin/lesson")
@Slf4j
@RequiredArgsConstructor
public class AdminLessonController {
    private final AdminLessonService adminLessonService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> LessonAdd(@RequestBody LessonRegisterRequest registerRequest){
        log.info("/admin/lesson/register : {}", registerRequest.toString());
        adminLessonService.addLesson(registerRequest);

        return ResponseEntity.ok(CommonResponse.from("강좌 등록이 완료되었습니다."));
    }
}
