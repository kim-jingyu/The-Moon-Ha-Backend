package com.innerpeace.themoonha.domain.live.controller;

import com.innerpeace.themoonha.domain.live.dto.LiveLessonDetailResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonRequest;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonStatusResponse;
import com.innerpeace.themoonha.domain.live.service.LiveLessonEventConsumer;
import com.innerpeace.themoonha.domain.live.service.LiveLessonEventService;
import com.innerpeace.themoonha.domain.live.service.LiveLessonService;
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
    private final LiveLessonEventConsumer liveLessonEventConsumer;
    private final LiveLessonEventService liveLessonEventService;

    @GetMapping("/enrolled")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithMember(@RequestParam Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsByMember(memberId));
    }

    @GetMapping("/not-enrolled")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithoutMember(@RequestParam Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsMemberDoesNotHave(memberId));
    }

    @GetMapping("/{liveId}")
    public ResponseEntity<LiveLessonDetailResponse> retrieveLiveLessonDetail(@PathVariable Long liveId, @RequestParam Long memberId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonDetails(liveId, memberId));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<LiveLessonResponse> makeLiveLesson(@RequestPart LiveLessonRequest request,
                                                             @RequestPart MultipartFile thumbnail) {
        return ResponseEntity.status(CREATED).body(liveLessonService.createLiveLesson(request, thumbnail));
    }

    @PostMapping("/{liveId}/end")
    public ResponseEntity<Void> endLiveLesson(@PathVariable Long liveId) {
        liveLessonService.endLiveLesson(liveId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{liveId}/status")
    public ResponseEntity<LiveLessonStatusResponse> retrieveLiveLessonStatus(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonStatus(liveId));
    }

    @GetMapping("/{liveId}/viewers")
    public ResponseEntity<Integer> getViewersCount(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonEventConsumer.getViewersCount(liveId));
    }

    @GetMapping("/{liveId}/likes")
    public ResponseEntity<Integer> getLLikesCount(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonEventConsumer.getLikesCount(liveId));
    }

    @PostMapping("/{liveId}/join")
    public ResponseEntity<Void> joinLiveLesson(@PathVariable Long liveId, @RequestParam Long memberId) {
        liveLessonEventService.sendViewerJoinedEvent(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{liveId}/leave")
    public ResponseEntity<Void> leaveLiveLesson(@PathVariable Long liveId, @RequestParam Long memberId) {
        liveLessonEventService.sendViewerLeftEvent(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{liveId}/like")
    public ResponseEntity<Void> likeLiveLesson(@PathVariable Long liveId, @RequestParam Long memberId) {
        liveLessonEventService.sendLikeEvent(liveId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{liveId}/share")
    public ResponseEntity<String> getShareLink(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getShareLink(liveId));
    }
}
