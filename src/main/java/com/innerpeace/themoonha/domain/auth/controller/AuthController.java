package com.innerpeace.themoonha.domain.auth.controller;

import com.innerpeace.themoonha.domain.auth.dto.JwtDTO;
import com.innerpeace.themoonha.domain.auth.dto.LoginRequest;
import com.innerpeace.themoonha.domain.auth.dto.SignUpRequest;
import com.innerpeace.themoonha.domain.auth.service.AuthService;
import com.innerpeace.themoonha.domain.auth.util.AuthUtil;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
 * 2024.08.26   최유경       로그인 API
 * 2024.09.13   최유경       로그아웃 구현
 * </pre>
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value="/signup")
    public ResponseEntity<CommonResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
        return ResponseEntity.ok(CommonResponse.from(SuccessCode.AUTH_SIGNUP_SUCCESS.getMessage()));
    }

    @PostMapping(value="/login")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        // 1. 회원 여부 확인
        JwtDTO jwtDTO = authService.login(loginRequest);

        // 2. AccessToken Header 추가
        Cookie accessTokenCookie = AuthUtil.createJwtTokenCookie("accessToken", jwtDTO.getAccessToken());
        response.addCookie(accessTokenCookie);

        // 3. RefreshToken Cookie 추가
        Cookie refreshTokenCookie = AuthUtil.createJwtTokenCookie("refreshToken", jwtDTO.getRefreshToken());
        response.addCookie(refreshTokenCookie);

        return ResponseEntity
                .ok(CommonResponse.from(SuccessCode.AUTH_LOGIN_SUCCESS.getMessage()));
    }

    @PostMapping("/logout")
    public ResponseEntity<CommonResponse> logOut(HttpServletResponse response) {
        Cookie accessTokenDeleteCookie = AuthUtil.createJwtTokenDeleteCookie("accessToken");
        Cookie refreshTokenDeleteCookie = AuthUtil.createJwtTokenDeleteCookie("refreshToken");

        response.addCookie(accessTokenDeleteCookie);
        response.addCookie(refreshTokenDeleteCookie);

        return ResponseEntity.ok(new CommonResponse(true, "로그아웃 성공"));
    }
}
