package com.innerpeace.themoonha.domain.alim.controller;

import com.innerpeace.themoonha.domain.alim.service.AlimService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 알림 컨트롤러
 * @author 조희정
 * @since 2024.09.11
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.11  	조희정       최초 생성
 * 2024.09.11  	조희정       FCM 토큰 저장 기능 구현
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/alim")
@Slf4j
public class AlimController {

    private final AlimService alimService;

    @PostMapping("/token")
    public ResponseEntity<CommonResponse> fcmTokenAdd(@RequestBody String token) {
        Long memberId = 1L;
        return ResponseEntity.ok(alimService.addFcmToken(memberId, token));
    }

}
