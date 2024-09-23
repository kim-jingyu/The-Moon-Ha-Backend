package com.innerpeace.themoonha.domain.auth.service;

import com.innerpeace.themoonha.domain.auth.dto.JwtDTO;
import com.innerpeace.themoonha.domain.auth.dto.LoginRequest;
import com.innerpeace.themoonha.domain.auth.dto.SignUpRequest;

/**
 * 회원가입/로그인 서비스
 * @author 최유경
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	최유경       최초 생성
 * 2024.08.26  	최유경       로그인 메서드 생성
 * 2024.08.27   최유경       토큰 만료 재발급 로직
 * </pre>
 */
public interface AuthService {
    /**
     * 회원가입 메서드
     *
     * @param signUpRequest 회원가입 요청
     * @return
     */
    int signUp(SignUpRequest signUpRequest);
    /**
     * 사용자 이름 확인 메서드
     *
     * @param username 사용자 이름
     * @return
     */
    boolean checkAvailableUsername(String username);
    /**
     * 로그인
     *
     * @param loginRequest 로그인 요청
     * @return
     */
    JwtDTO login(LoginRequest loginRequest);
    /**
     * 토큰 재발급
     * @param refreshToken 리프레시 토큰
     * @return
     */
    JwtDTO regenerateToken(String refreshToken);
}
