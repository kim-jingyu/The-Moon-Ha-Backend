package com.innerpeace.themoonha.domain.lesson.controller;

import com.innerpeace.themoonha.domain.lesson.dto.*;
import com.innerpeace.themoonha.domain.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 강좌 컨트롤러
 *
 * @author 손승완
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.24  	손승완       최초 생성
 * 2024.08.25   손승완       강좌 상세보기 기능 추가
 * 2024.08.25   손승완       숏폼 상세보기 기능 추가
 * 2024.08.26   손승완       강사 상세보기 기능 추가
 * 2024.08.26   손승완       장바구니 조회 기능 추가
 * </pre>
 * @since 2024.08.24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
@Slf4j
public class LessonController {
    private final LessonService lessonService;

    /**
     * 강좌 목록 조회
     *
     * @param lessonListRequest
     * @author 손승완
     */
    @GetMapping("/list")
    public ResponseEntity<LessonListResponse> lessonList(LessonListRequest lessonListRequest) {
        return ResponseEntity.ok(lessonService.findLessonList(lessonListRequest));
    }

    /**
     * 강좌 상세 조회
     *
     * @param lessonId
     * @author 손승완
     */
    @GetMapping("/detail/{lessonId}")
    public ResponseEntity<LessonDetailResponse> lessonDetail(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonService.findLessonDetail(lessonId));
    }

    /**
     * 숏폼 상세 조회
     *
     * @param shortFormId
     * @author 손승완
     */
    @GetMapping("/shortform/{shortFormId}")
    public ResponseEntity<ShortFormDetailResponse> shortFormDetail(@PathVariable Long shortFormId) {
        return ResponseEntity.ok(lessonService.findShortFormDetail(shortFormId));
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<TutorDetailResponse> tutorDetail(@PathVariable Long tutorId) {
        return ResponseEntity.ok(lessonService.findTutorDetail(tutorId));
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartResponse>> cartList() {
        Long memberId = 1L;
        return ResponseEntity.ok(lessonService.findCartList(memberId));
    }

}
