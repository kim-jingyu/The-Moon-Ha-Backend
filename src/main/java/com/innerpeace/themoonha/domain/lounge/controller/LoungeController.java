package com.innerpeace.themoonha.domain.lounge.controller;

import com.innerpeace.themoonha.domain.lounge.dto.*;
import com.innerpeace.themoonha.domain.lounge.service.LoungeService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
 * 2024.08.28  	조희정       게시글 수정, 댓글 삭제, 댓글 수정 구현
 * 2024.08.29  	조희정       출석 시작 구현
 * 2024.08.30  	조희정       수강생 출석 여부 수정 구현
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
        String role = "ROLE_MEMBER";
        return ResponseEntity.ok(loungeService.findLoungeList(memberId, role));
    }

    /**
     * 라운지 홈 조회
     * @param loungeId
     * @return
     */
    @GetMapping("/{loungeId}/home")
    public ResponseEntity<LoungeHomeResponse> loungeHomeList(@PathVariable Long loungeId) {
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
    public ResponseEntity<LoungePostDetailDTO> loungePostDetails(@PathVariable Long loungeId, @PathVariable Long loungePostId) {
        return ResponseEntity.ok(loungeService.findLoungePostDetail(loungePostId));
    }

    /**
     * 라운지 게시글 등록
     * @param loungePostRequest
     * @return
     */
    @PostMapping("/post/register")
    public ResponseEntity<CommonResponse> loungePostAdd(@RequestPart("loungePostRequest") LoungePostRequest loungePostRequest,
                                                             @RequestPart(value = "files", required = false) List<MultipartFile> loungePostImgs) {
        Long memberId = 1L;
        return ResponseEntity.ok(loungeService.addLoungePost(loungePostRequest, memberId, loungePostImgs));
    }
        

    /**
     * 라운지 댓글 등록
     * @param loungeCommentRequest
     * @return
     */
    @PostMapping("/comment/register")
    public ResponseEntity<CommonResponse> loungeCommentAdd(@RequestBody LoungeCommentRequest loungeCommentRequest) {
        Long memberId = 1L;
        return ResponseEntity.ok(loungeService.addLoungeComment(loungeCommentRequest, memberId));
    }

    /**
     * 라운지 게시글 수정
     * @param loungePostId
     * @param loungePostRequest
     * @return
     */
    @PostMapping("/post/{loungePostId}/edit")
    public ResponseEntity<CommonResponse> loungePostModify(@PathVariable Long loungePostId,
                                                         @RequestPart("loungePostRequest") LoungePostRequest loungePostRequest,
                                                         @RequestPart(value = "addfiles", required = false) List<MultipartFile> imgsToAdd,
                                                         @RequestPart(value = "deletefiles", required = false) List<String> imgsToDelete) {
        return ResponseEntity.ok(loungeService.modifyLoungePost(loungePostId, loungePostRequest, imgsToAdd, imgsToDelete));
    }

    /**
     * 라운지 게시글 삭제
     * @param loungePostId
     * @return
     */
    @DeleteMapping("/post/{loungePostId}/delete")
    public ResponseEntity<CommonResponse> loungePostRemove(@PathVariable Long loungePostId) {
        return ResponseEntity.ok(loungeService.removeLoungePost(loungePostId));
    }

    /**
     * 라운지 댓글 삭제
     * @param loungeCommentId
     * @return
     */
    @DeleteMapping("/comment/{loungeCommentId}/delete")
    public ResponseEntity<CommonResponse> loungeCommentRemove(@PathVariable Long loungeCommentId) {
        return ResponseEntity.ok(loungeService.removeLoungeComment(loungeCommentId));
    }

    /**
     * 라운지 댓글 수정
     * @param loungeCommentId
     * @param loungeCommentUpdateRequest
     * @return
     */
    @PostMapping("/comment/{loungeCommentId}/edit")
    public ResponseEntity<CommonResponse> loungeCommentUpdate(@PathVariable Long loungeCommentId, @RequestBody LoungeCommentUpdateRequest loungeCommentUpdateRequest) {
        return ResponseEntity.ok(loungeService.modifyLoungeComment(loungeCommentUpdateRequest));
    }

    /**
     * 출석 시작
     * @param lessonId
     * @return
     */
    @PostMapping("/attendance")
    public ResponseEntity<List<AttendanceDTO>> loungeAttendanceList(@RequestBody Long lessonId) {
        return ResponseEntity.ok(loungeService.saveAttendanceList(lessonId));
    }

    /**
     * 수강생 출석 여부 수정
     * @param attendanceId
     * @return
     */
    @PostMapping("/attendance/update")
    public ResponseEntity<CommonResponse> loungeAttendanceUpdate(@RequestBody Long attendanceId) {
        return ResponseEntity.ok(loungeService.modifyAttendanceYn(attendanceId));
    }

}
