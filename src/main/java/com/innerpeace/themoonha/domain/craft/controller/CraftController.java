package com.innerpeace.themoonha.domain.craft.controller;

import com.innerpeace.themoonha.domain.craft.dto.CraftMainResponse;
import com.innerpeace.themoonha.domain.craft.dto.PrologueDetailResponse;
import com.innerpeace.themoonha.domain.craft.service.CraftService;
import com.innerpeace.themoonha.global.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 문화공방 컨트롤러
 * @author 손승완
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	손승완       최초 생성
 * 2024.08.26   손승완       프롤로그 상세 조회 구현
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/craft")
public class CraftController {
    private final CraftService craftService;
    @GetMapping("/list")
    public ResponseEntity<CraftMainResponse> craftMain(Criteria criteria) {
        return ResponseEntity.ok(craftService.findCraftMain(criteria));
    }

    @GetMapping("/prologue/{prologueId}")
    public ResponseEntity<PrologueDetailResponse> prologueDetail(@PathVariable Long prologueId) {
        return ResponseEntity.ok(craftService.findPrologueDetail(prologueId));
    }
}
