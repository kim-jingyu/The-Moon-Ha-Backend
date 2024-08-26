package com.innerpeace.themoonha.domain.lounge.controller;

import com.innerpeace.themoonha.domain.lounge.dto.LoungeListResponse;
import com.innerpeace.themoonha.domain.lounge.service.LoungeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * 2024.08.25  	조희정       loungeList 메서드 추가
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
}
