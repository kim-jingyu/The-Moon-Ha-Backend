package com.innerpeace.themoonha.domain.auth.service;

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
 * </pre>
 */
public interface AuthService {
    int signUp(SignUpRequest request);
    boolean checkAvailableUsername(String userName);
}
