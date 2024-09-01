package com.innerpeace.themoonha.domain.bite.controller;

import com.innerpeace.themoonha.domain.bite.dto.field.FieldRequest;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldResponseForDetail;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldResponseForList;
import com.innerpeace.themoonha.domain.bite.dto.field.FieldSearchResponse;
import com.innerpeace.themoonha.domain.bite.service.field.FieldService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 분야별 한 입 컨트롤러
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  김진규        최초 생성
 * </pre>
 * @since 2024.08.31
 */
@RequestMapping("/bite/field")
@RestController
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;

    /**
     * 분야별 한 입 전체 콘텐츠 목록 조회 API
     * @return 분야별 한 입 콘텐츠 목록
     */
    @GetMapping
    public ResponseEntity<List<FieldResponseForList>> retrieveFieldList() {
        return ResponseEntity.ok(fieldService.getFieldList());
    }

    /**
     * 분야별 한 입 콘텐츠 상세 조회 API
     * @param id
     * @return 분야별 한 입 콘텐츠
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<FieldResponseForDetail> retrieveFieldContent(@PathVariable Long id) {
        return ResponseEntity.ok(fieldService.getFieldContent(id));
    }

    /**
     * 분야별 한 입 콘텐츠 등록 API
     * @param fieldRequest
     * @param thumbnail
     * @param content
     * @return 등록된 분야별 한 입 콘텐츠 아이디
     * @throws IOException
     */
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<CommonResponse> makeBeforeAfter(@RequestPart FieldRequest fieldRequest,
                                                          @RequestPart MultipartFile thumbnail,
                                                          @RequestPart MultipartFile content) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fieldService.makeField(1L, fieldRequest, thumbnail, content));
    }

    /**
     * 분야별 한 입 타이틀 기반 콘텐츠 검색 API
     * @param keyword
     * @return 타이틀 기반으로 검색된 분야별 한 입 콘텐츠 목록
     */
    @GetMapping("/search/title")
    public ResponseEntity<List<FieldSearchResponse>> searchBeforeAfterByTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(fieldService.findFieldByTitle(keyword));
    }

    /**
     * 분야별 한 입 해시태그 기반 콘텐츠 검색 API
     * @param hashtags
     * @return 해시태그 기반으로 검색된 분야별 한 입 콘텐츠 목록
     */
    @GetMapping("/search/hashtag")
    public ResponseEntity<List<FieldSearchResponse>> searchBeforeAfterByHashtag(@RequestParam(value = "tag") List<String> hashtags) {
        return ResponseEntity.ok(fieldService.findFieldByHashTags(hashtags));
    }
}
