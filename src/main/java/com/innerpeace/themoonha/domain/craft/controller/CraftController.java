package com.innerpeace.themoonha.domain.craft.controller;

import com.innerpeace.themoonha.domain.craft.dto.CraftMainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/craft")
public class CraftController {
    @GetMapping("/list")
    public ResponseEntity<CraftMainResponse> craftMain() {

    }
}
