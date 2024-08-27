package com.innerpeace.themoonha.domain.bite.controller;

import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterRequest;
import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterResponse;
import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterSearchResponse;
import com.innerpeace.themoonha.domain.bite.service.BeforeAfterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 비포애프터 컨트롤러
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  김진규        최초 생성
 * </pre>
 * @since 2024.08.27
 */
@RequestMapping("/beforeafter")
@RestController
@RequiredArgsConstructor
public class BeforeAfterController {
    private final BeforeAfterService beforeAfterService;

    @GetMapping
    public ResponseEntity<List<BeforeAfterResponse>> retrieveBeforeAfterList() {
        return ResponseEntity.ok(beforeAfterService.getBeforeAfterList());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Long> makeBeforeAfter(@RequestPart Long memberId,
                                                @RequestPart BeforeAfterRequest beforeAfterRequest,
                                                @RequestPart MultipartFile beforeContent,
                                                @RequestPart MultipartFile afterContent) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(beforeAfterService.makeBeforeAfter(memberId, beforeAfterRequest, beforeContent, afterContent));
    }

    @GetMapping("/bytitle")
    public ResponseEntity<List<BeforeAfterSearchResponse>> searchBeforeAfterByTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(beforeAfterService.findBeforeAfterByTitle(keyword));
    }

    @GetMapping("/byhashtag")
    public ResponseEntity<List<BeforeAfterSearchResponse>> searchBeforeAfterByHashtag(@RequestParam(value = "tag") List<String> hashtags) {
        return ResponseEntity.ok(beforeAfterService.findBeforeAfterByHashTags(hashtags));
    }
}
