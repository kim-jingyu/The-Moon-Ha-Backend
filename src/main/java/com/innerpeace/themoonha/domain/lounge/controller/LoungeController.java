package com.innerpeace.themoonha.domain.lounge.controller;

import com.innerpeace.themoonha.domain.lounge.dto.*;
import com.innerpeace.themoonha.domain.lounge.service.LoungeService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
 * 2024.08.25  	조희정       라운지 목록 조회 기능 구현
 * 2024.08.26  	조희정       라운지 홈 조회, 게시글 상세 조회 구현
 * 2024.08.27  	조희정       게시글 생성, 삭제, 수정 구현
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

    /**
     * 라운지 게시글 등록
     * @param loungePostRequest
     * @return
     */
    @PostMapping("/post/register")
    public ResponseEntity<CommonResponse> loungePostRegister(@RequestBody LoungePostRequest loungePostRequest) {
        Long memberId = 1L;
        return ResponseEntity.ok(loungeService.addLoungePost(loungePostRequest, memberId));
    }

    /**
     * 라운지 댓글 등록
     * @param loungeCommentRequest
     * @return
     */
    @PostMapping("/comment/register")
    public ResponseEntity<CommonResponse> loungeCommentRegister(@RequestBody LoungeCommentRequest loungeCommentRequest) {
        Long memberId = 1L;
        return ResponseEntity.ok(loungeService.addLoungeComment(loungeCommentRequest, memberId));
    }

    /**
     * 라운지 게시글 수정
     * @param loungePostId
     * @param loungePostRequest
     * @return
     */
    @PatchMapping("/post/{loungePostId}/edit")
    public ResponseEntity<CommonResponse> loungePostEdit(@PathVariable Long loungePostId, @RequestBody LoungePostRequest loungePostRequest) {
        return ResponseEntity.ok(loungeService.modifyLoungePost(loungePostId, loungePostRequest));
    }

    /**
     * 라운지 게시글 삭제
     * @param loungePostId
     * @return
     */
    @DeleteMapping("/post/{loungePostId}/delete")
    public ResponseEntity<CommonResponse> loungePostDelete(@PathVariable Long loungePostId) {
        return ResponseEntity.ok(loungeService.deleteLoungePost(loungePostId));
    }
}
