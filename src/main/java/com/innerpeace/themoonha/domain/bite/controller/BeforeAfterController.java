package com.innerpeace.themoonha.domain.bite.controller;

import com.innerpeace.themoonha.domain.bite.dto.beforeafter.BeforeAfterRequest;
import com.innerpeace.themoonha.domain.bite.dto.beforeafter.BeforeAfterDetailResponse;
import com.innerpeace.themoonha.domain.bite.dto.beforeafter.BeforeAfterListResponse;
import com.innerpeace.themoonha.domain.bite.dto.beforeafter.BeforeAfterSearchResponse;
import com.innerpeace.themoonha.domain.bite.service.beforeafter.BeforeAfterService;
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
     * 비포애프터 전체 콘텐츠 목록 조회 API (최신순)
     * @return 비포애프터 콘텐츠 목록
     */
    @GetMapping(value = "/by-latest")
    public ResponseEntity<List<BeforeAfterListResponse>> retrieveBeforeAfterList() {
        return ResponseEntity.ok(beforeAfterService.getBeforeAfterList());
    }

    /**
     * 비포애프터 전체 콘텐츠 목록 조회 API (제목순)
     * @return 비포애프터 콘텐츠 목록
     */
    @GetMapping(value = "/by-title")
    public ResponseEntity<List<BeforeAfterListResponse>> retrieveBeforeAfterListOrderByTitle() {
        return ResponseEntity.ok(beforeAfterService.getBeforeAfterListOrderByTitle());
    }

    /**
     * 비포애프터 콘텐츠 상세 조회 API
     * @param id
     * @return 비포애프터 콘텐츠
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<BeforeAfterDetailResponse> retrieveBeforeAfterContent(@PathVariable Long id) {
        return ResponseEntity.ok(beforeAfterService.getBeforeAfterContent(id));
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
                                                        @RequestPart MultipartFile beforeThumbnail,
                                                        @RequestPart MultipartFile afterThumbnail,
                                                        @RequestPart MultipartFile beforeContent,
                                                        @RequestPart MultipartFile afterContent){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(beforeAfterService.makeBeforeAfter(1L, beforeAfterRequest, beforeThumbnail, afterThumbnail, beforeContent, afterContent));
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
