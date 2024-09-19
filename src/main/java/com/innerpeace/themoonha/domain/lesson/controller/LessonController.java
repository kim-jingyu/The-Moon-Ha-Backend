package com.innerpeace.themoonha.domain.lesson.controller;

import com.innerpeace.themoonha.domain.lesson.dto.*;
import com.innerpeace.themoonha.domain.lesson.service.LessonService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.util.MemberId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 강좌 컨트롤러
 *
 * @author 손승완
 * @since 2024.08.24
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.24  	손승완       최초 생성
 * 2024.08.25   손승완       강좌 상세보기 기능 추가
 * 2024.08.25   손승완       숏폼 상세보기 기능 추가
 * 2024.08.26   손승완       강사 상세보기 기능 추가
 * 2024.08.26   손승완       장바구니 및 신청 기능 추가
 * </pre>
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
    public ResponseEntity<LessonListResponse> lessonList(LessonListRequest lessonListRequest,
                                                         @MemberId Long memberId) {
        return ResponseEntity.ok(lessonService.findLessonList(lessonListRequest, memberId));
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

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<TutorDetailResponse> tutorDetail(@PathVariable Long tutorId) {
        return ResponseEntity.ok(lessonService.findTutorDetail(tutorId));
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartResponse>> cartList(@MemberId Long memberId) {
        return ResponseEntity.ok(lessonService.findCartList(memberId));
    }

    @PostMapping("/cart")
    public ResponseEntity<CommonResponse> cartSave(@RequestBody CartRequest cartRequest,
                                                   @MemberId Long memberId) {
        cartRequest.setMemberId(memberId);
        return ResponseEntity.ok(lessonService.addCart(cartRequest));
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<CommonResponse> cartRemove(@PathVariable Long cartId,
                                                     @MemberId Long memberId) {
        return ResponseEntity.ok(lessonService.removeCart(cartId, memberId));
    }

    @PostMapping("/pay")
    public ResponseEntity<CommonResponse> lessonPayment(@RequestBody SugangRequest sugangRequest,
                                                        @MemberId Long memberId) {
        return ResponseEntity.ok(lessonService.payLesson(sugangRequest.getCartIdList(), memberId));
    }

    @GetMapping("/enroll")
    public ResponseEntity<List<LessonEnrollResponse>> lessonEnrollList(@MemberId Long memberId) {
        return ResponseEntity.ok(lessonService.findEnrollLessonList(memberId));
    }

    @GetMapping("/field/enroll")
    public ResponseEntity<List<LessonEnrollResponse>> lessonFieldEnrollList(@MemberId Long memberId) {
        return ResponseEntity.ok(lessonService.findFieldEnrollLessonList(memberId));
    }

    @GetMapping("/by-tutor")
    public ResponseEntity<List<TutorLessonResponse>> tutorLessonList(@MemberId Long memberId) {
        return ResponseEntity.ok(lessonService.findTutorLessonList(memberId));
    }

    @GetMapping("/shortform/{shortFormId}")
    public void shortFormDetail(@PathVariable Long shortFormId,
                                @MemberId Long memberId) {
        lessonService.increaseShortFormViewCountCache(shortFormId, memberId);
    }

}
