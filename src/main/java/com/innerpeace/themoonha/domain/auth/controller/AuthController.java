package com.innerpeace.themoonha.domain.auth.controller;

import com.innerpeace.themoonha.domain.auth.dto.SignUpRequest;
import com.innerpeace.themoonha.domain.auth.service.AuthService;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원가입/로그인 컨트롤러
 * @author 최유경
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	최유경       최초 생성
 * </pre>
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value="/signup")
    public ResponseEntity<CommonResponse> signUp(@RequestBody SignUpRequest dto) {
        return authService.signUp(dto) == 1 ?
                ResponseEntity.ok(CommonResponse.from("회원가입 성공")) :
                ResponseEntity.ok(CommonResponse.of(false, "회원가입 실패"));
    }
}
