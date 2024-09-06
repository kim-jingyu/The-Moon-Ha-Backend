package com.innerpeace.themoonha.domain.live.controller;

import com.innerpeace.themoonha.domain.live.dto.LiveLessonDetailResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonRequest;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonStatusResponse;
import com.innerpeace.themoonha.domain.live.service.LiveLessonEventConsumer;
import com.innerpeace.themoonha.domain.live.service.LiveLessonEventService;
import com.innerpeace.themoonha.domain.live.service.LiveLessonService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
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

    @GetMapping("/enrolled/by-latest")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithMember() {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsByMember(1L));
    }

    @GetMapping("/enrolled/by-title")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithMemberOrderByTitle() {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsByMember(1L));
    }

    @GetMapping("/not-enrolled/by-latest")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithoutMember() {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsMemberDoesNotHave(1L));
    }

    @GetMapping("/not-enrolled/by-title")
    public ResponseEntity<List<LiveLessonResponse>> retrieveLiveLessonListWithoutMemberOrderByTitle() {
        return ResponseEntity.ok(liveLessonService.getLiveLessonsMemberDoesNotHave(1L));
    }

    @GetMapping("/{liveId}")
    public ResponseEntity<LiveLessonDetailResponse> retrieveLiveLessonDetail(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getLiveLessonDetails(liveId, 1L));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<LiveLessonResponse> makeLiveLesson(@RequestPart LiveLessonRequest liveLessonRequest,
                                                             @RequestPart MultipartFile thumbnail) {
        return ResponseEntity.status(CREATED).body(liveLessonService.createLiveLesson(1L ,liveLessonRequest, thumbnail));
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
        return ResponseEntity.ok(liveLessonEventConsumer.getViewersCount(liveId));
    }

    @GetMapping("/{liveId}/likes")
    public ResponseEntity<Integer> getLLikesCount(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonEventConsumer.getLikesCount(liveId));
    }

    @PostMapping("/{liveId}/join")
    public ResponseEntity<Void> joinLiveLesson(@PathVariable Long liveId) {
        liveLessonEventService.sendViewerJoinedEvent(liveId, 1L);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{liveId}/leave")
    public ResponseEntity<Void> leaveLiveLesson(@PathVariable Long liveId) {
        liveLessonEventService.sendViewerLeftEvent(liveId, 1L);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{liveId}/like")
    public ResponseEntity<Void> likeLiveLesson(@PathVariable Long liveId) {
        liveLessonEventService.sendLikeEvent(liveId, 1L);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{liveId}/share")
    public ResponseEntity<String> getShareLink(@PathVariable Long liveId) {
        return ResponseEntity.ok(liveLessonService.getShareLink(liveId));
    }
}
