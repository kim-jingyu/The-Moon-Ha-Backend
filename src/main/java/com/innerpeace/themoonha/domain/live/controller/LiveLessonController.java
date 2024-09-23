package com.innerpeace.themoonha.domain.live.controller;

import com.innerpeace.themoonha.domain.live.dto.LiveLessonDetailResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonRequest;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonStatusResponse;
import com.innerpeace.themoonha.domain.live.service.LiveLessonService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.util.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * 실시간 강좌 컨트롤러
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  김진규        최초 생성
 * </pre>
 * @since 2024.09.01
 */
@RestController
@RequestMapping("/live")
@RequiredArgsConstructor
public class LiveLessonController {
    private final LiveLessonService liveLessonService;

    /**
     * 멤버가 소유한 실시간 강좌 목록 조회 (최신순)
     * @param memberId
     * @return 멤버가 소유한 실시간 강좌 목록 (최신순)
     */
    @GetMapping("/enrolled/by-latest")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithMember(@MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsByMember(memberId));
    }

    /**
     * 멤버가 소유한 실시간 강좌 목록 조회 (제목순)
     * @param memberId
     * @return 멤버가 소유한 실시간 강좌 목록 (제목순)
     */
    @GetMapping("/enrolled/by-title")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithMemberOrderByTitle(@MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsByMemberOrderByTitle(memberId));
    }

    /**
     * 멤버가 소유하지 않은 실시간 강좌 목록 조회 (최신순)
     * @param memberId
     * @return 멤버가 소유한 실시간 강좌 목록 (최신순)
     */
    @GetMapping("/not-enrolled/by-latest")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithoutMember(@MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsMemberDoesNotHave(memberId));
    }

    /**
     * 멤버가 소유하지 않은 실시간 강좌 목록 조회 (제목순)
     * @param memberId
     * @return 멤버가 소유한 실시간 강좌 목록 (제목순)
     */
    @GetMapping("/not-enrolled/by-title")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithoutMemberOrderByTitle(@MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsMemberDoesNotHaveOrderByTitle(memberId));
    }

    /**
     * 실시간 강좌 상세정보 조회
     * @param liveId
     * @param memberId
     * @return 실시간 강좌 상세정보
     */
    @GetMapping("/{liveId}")
    public ResponseEntity<LiveLessonDetailResponse> retrieveLiveLessonDetail(@PathVariable Long liveId, @MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonDetails(liveId, memberId));
    }

    /**
     * 실시간 강좌 만들기
     * @param liveLessonRequest
     * @param thumbnail
     * @param memberId
     * @return 실시간 강좌 응답 DTO
     */
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<LiveLessonResponse> makeLiveLesson(@RequestPart LiveLessonRequest liveLessonRequest,
                                                             @RequestPart MultipartFile thumbnail,
                                                             @MemberId Long memberId) {
        return ResponseEntity.status(CREATED).body(liveLessonService.createLiveLesson(memberId ,liveLessonRequest, thumbnail));
    }

    /**
     * 실시간 강좌 종료
     * @param liveId
     * @return 공통 응답 DTO
     */
    @PostMapping("/{liveId}/end")
    public ResponseEntity<CommonResponse> endLiveLesson(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.endLiveLesson(liveId));
    }

    /**
     * 실시간 강좌 상태 받아오기
     * @param liveId
     * @return 실시간 강좌 상태 응답 DTO
     */
    @GetMapping("/{liveId}/status")
    public ResponseEntity<LiveLessonStatusResponse> retrieveLiveLessonStatus(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonStatus(liveId));
    }

    /**
     * 실시간 강좌 조회수 가져오기
     * @param liveId
     * @return 실시간 강좌의 조회수
     */
    @GetMapping("/{liveId}/viewers")
    public ResponseEntity<Integer> getViewersCount(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getViewsCount(liveId));
    }

    /**
     * 실시간 강좌 좋아요 가져오기
     * @param liveId
     * @return 실시간 강좌의 좋아요 수
     */
    @GetMapping("/{liveId}/likes")
    public ResponseEntity<Integer> getLLikesCount(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getLikesCount(liveId));
    }

    /**
     * 실시간 강좌 참여하기
     * @param liveId
     * @param memberId
     * @return
     */
    @PostMapping("/{liveId}/join")
    public ResponseEntity<Void> joinLiveLesson(@PathVariable Long liveId, @MemberId Long memberId) {
        liveLessonService.joinLiveLesson(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    /**
     * 실시간 강좌 나가기
     * @param liveId
     * @param memberId
     * @return
     */
    @PostMapping("/{liveId}/leave")
    public ResponseEntity<Void> leaveLiveLesson(@PathVariable Long liveId, @MemberId Long memberId) {
        liveLessonService.leaveLiveLesson(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    /**
     * 실시간 강좌 좋아요 하기
     * @param liveId
     * @param memberId
     * @return
     */
    @PostMapping("/{liveId}/like")
    public ResponseEntity<Void> likeLiveLesson(@PathVariable Long liveId, @MemberId Long memberId) {
        liveLessonService.likeLiveLesson(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    /**
     * 실시간 강좌 공유하기
     * @param liveId
     * @return
     */
    @GetMapping("/{liveId}/share")
    public ResponseEntity<String> getShareLink(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getShareLink(liveId));
    }
}
