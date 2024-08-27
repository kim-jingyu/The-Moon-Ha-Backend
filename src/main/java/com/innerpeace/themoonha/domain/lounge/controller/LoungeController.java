package com.innerpeace.themoonha.domain.lounge.controller;

import com.innerpeace.themoonha.domain.lounge.dto.*;
import com.innerpeace.themoonha.domain.lounge.service.LoungeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 라운지 컨트롤러
 * @author 조희정
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	조희정       최초 생성
 * 2024.08.25  	조희정       loungeList 메서드 추가
 * 2024.08.26  	조희정       loungeHome, loungePostDetail 메서드 추가
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/lounge")
@Slf4j
public class LoungeController {

    private final LoungeService loungeService;

    /**
     * 라운지 목록 조회
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<LoungeListResponse>> loungeList() {
        Long memberId = 1L; // 임시 memberId
        return ResponseEntity.ok(loungeService.findLoungeList(memberId));
    }

    /**
     * 라운지 홈 조회
     * @param loungeId
     * @return
     */
    @GetMapping("/{loungeId}/home")
    public ResponseEntity<LoungeHomeResponse> loungeHome(@PathVariable Long loungeId) {
        Long memberId = 1L; // 임시 memberId
        String role = "ROLE_TUTOR"; // 임시 role
        return ResponseEntity.ok(loungeService.findLoungeHome(loungeId, memberId, role));
    }

    /**
     * 라운지 게시글 상세 조회
     * @param loungeId
     * @param loungePostId
     * @return
     */
    @GetMapping("/{loungeId}/post/{loungePostId}")
    public ResponseEntity<LoungePostDetailDTO> loungePostDetail(@PathVariable Long loungeId, @PathVariable Long loungePostId) {
        return ResponseEntity.ok(loungeService.findLoungePostDetail(loungePostId));
    }
}
