package com.innerpeace.themoonha.domain.bite.controller;

import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterRequest;
import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterResponse;
import com.innerpeace.themoonha.domain.bite.dto.BeforeAfterSearchResponse;
import com.innerpeace.themoonha.domain.bite.service.BeforeAfterService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
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
 * 2024.08.27  김진규        retrieveBeforeAfterList, makeBeforeAfter 메서드 추가
 * 2024.08.28  김진규        searchBeforeAfterByTitle, searchBeforeAfterByHashtag
 * </pre>
 * @since 2024.08.27
 */
@RequestMapping("/bite/before-after")
@RestController
@RequiredArgsConstructor
public class BeforeAfterController {
    private final BeforeAfterService beforeAfterService;

    /**
     * 비포애프터 전체 콘텐츠 목록 조회 API
     * @return 비포애프터 콘텐츠 목록
     */
    @GetMapping
    public ResponseEntity<List<BeforeAfterResponse>> retrieveBeforeAfterList() {
        return ResponseEntity.ok(beforeAfterService.getBeforeAfterList());
    }

    /**
     * 비포애프터 콘텐츠 등록 API
     * @param beforeAfterRequest
     * @param beforeContent
     * @param afterContent
     * @return 등록된 비포애프터 콘텐츠 아이디
     * @throws IOException
     */
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<CommonResponse> makeBeforeAfter(@RequestPart BeforeAfterRequest beforeAfterRequest,
                                                        @RequestPart MultipartFile beforeContent,
                                                        @RequestPart MultipartFile afterContent) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(beforeAfterService.makeBeforeAfter(1L, beforeAfterRequest, beforeContent, afterContent));
    }

    /**
     * 비포애프터 타이틀 기반 콘텐츠 검색 API
     * @param keyword
     * @return 타이틀 기반으로 검색된 비포애프터 콘텐츠 목록
     */
    @GetMapping("/search/title")
    public ResponseEntity<List<BeforeAfterSearchResponse>> searchBeforeAfterByTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(beforeAfterService.findBeforeAfterByTitle(keyword));
    }

    /**
     * 비포애프터 해시태그 기반 콘텐츠 검색 API
     * @param hashtags
     * @return 해시태그 기반으로 검색된 비포애프터 콘텐츠 목록
     */
    @GetMapping("/search/hashtag")
    public ResponseEntity<List<BeforeAfterSearchResponse>> searchBeforeAfterByHashtag(@RequestParam(value = "tag") List<String> hashtags) {
        return ResponseEntity.ok(beforeAfterService.findBeforeAfterByHashTags(hashtags));
    }
}
