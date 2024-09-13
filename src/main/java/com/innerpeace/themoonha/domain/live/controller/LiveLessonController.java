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
    @GetMapping("/enrolled/by-latest")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithMember(@MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsByMember(memberId));
    }

    @GetMapping("/enrolled/by-title")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithMemberOrderByTitle(@MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsByMemberOrderByTitle(memberId));
    }

    @GetMapping("/not-enrolled/by-latest")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithoutMember(@MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsMemberDoesNotHave(memberId));
    }

    @GetMapping("/not-enrolled/by-title")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithoutMemberOrderByTitle(@MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsMemberDoesNotHaveOrderByTitle(memberId));
    }

    @GetMapping("/{liveId}")
    public ResponseEntity<LiveLessonDetailResponse> retrieveLiveLessonDetail(@PathVariable Long liveId, @MemberId Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonDetails(liveId, memberId));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<LiveLessonResponse> makeLiveLesson(@RequestPart LiveLessonRequest liveLessonRequest,
                                                             @RequestPart MultipartFile thumbnail,
                                                             @MemberId Long memberId) {
        return ResponseEntity.status(CREATED).body(liveLessonService.createLiveLesson(memberId ,liveLessonRequest, thumbnail));
    }

    @PostMapping("/{liveId}/end")
    public ResponseEntity<CommonResponse> endLiveLesson(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.endLiveLesson(liveId));
    }

    @GetMapping("/{liveId}/status")
    public ResponseEntity<LiveLessonStatusResponse> retrieveLiveLessonStatus(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonStatus(liveId));
    }

    @GetMapping("/{liveId}/viewers")
    public ResponseEntity<Integer> getViewersCount(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getViewsCount(liveId));
    }

    @GetMapping("/{liveId}/likes")
    public ResponseEntity<Integer> getLLikesCount(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getLikesCount(liveId));
    }

    @PostMapping("/{liveId}/join")
    public ResponseEntity<Void> joinLiveLesson(@PathVariable Long liveId, @MemberId Long memberId) {
        liveLessonService.joinLiveLesson(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{liveId}/leave")
    public ResponseEntity<Void> leaveLiveLesson(@PathVariable Long liveId, @MemberId Long memberId) {
        liveLessonService.leaveLiveLesson(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{liveId}/like")
    public ResponseEntity<Void> likeLiveLesson(@PathVariable Long liveId, @MemberId Long memberId) {
        liveLessonService.likeLiveLesson(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{liveId}/share")
    public ResponseEntity<String> getShareLink(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getShareLink(liveId));
    }
}
