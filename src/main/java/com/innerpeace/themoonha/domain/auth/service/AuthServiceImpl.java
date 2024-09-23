package com.innerpeace.themoonha.domain.auth.service;

import com.innerpeace.themoonha.domain.auth.dto.JwtDTO;
import com.innerpeace.themoonha.domain.auth.dto.LoginRequest;
import com.innerpeace.themoonha.domain.auth.dto.SignUpRequest;
import com.innerpeace.themoonha.domain.auth.jwt.JwtTokenProvider;
import com.innerpeace.themoonha.domain.auth.mapper.AuthMapper;
import com.innerpeace.themoonha.global.entity.Member;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원가입/로그인 서비스 구현체
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
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final AuthMapper authMapper;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입 메서드
     *
     * @param signUpRequest 회원가입 요청
     * @return
     */
    @Override
    public int signUp(SignUpRequest signUpRequest) {
        // 사용 가능한 username 여부 확인
        if(!checkAvailableUsername(signUpRequest.getUsername()))
            throw new CustomException(ErrorCode.MEMBER_DUPLICATE);

        // 비밀번호 암호화 처리
        String encodedPassword = encoder.encode(signUpRequest.getPassword());
        Member member = Member.of(signUpRequest,encodedPassword);

        int result = authMapper.insertMember(member);
        if(result != 1)
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);

        return result;
    }

    /**
     * 사용자 이름 확인 메서드
     *
     * @param username 사용자 이름
     * @return
     */
    @Override
    public boolean checkAvailableUsername(String username) {
        return !authMapper.selectByUsername(username).isPresent();
    }

    /**
     * 로그인
     *
     * @param loginRequest 로그인 요청
     * @return
     */
    @Override
    public JwtDTO login(LoginRequest loginRequest) {
        // 1. 회원인지 확인하기
        Member member =  authMapper.selectByUsername(loginRequest.getUsername())
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        // 2. 비밀번호 일치 여부 확인하기
        if(!encoder.matches(loginRequest.getPassword(), member.getPassword()))
            throw new CustomException(ErrorCode.MEMBER_INCORRECT_AUTH);

        // 3. 토큰 발급하기
        JwtDTO jwtDTO = jwtTokenProvider.generateToken(member);
        if(jwtDTO==null)
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);

        return jwtDTO;
    }

    /**
     * 토큰 재발급
     * @param refreshToken 리프레시 토큰
     * @return
     */
    @Override
    public JwtDTO regenerateToken(String refreshToken) {
        // 1. 회원인지 확인하기
        Claims claims = jwtTokenProvider.parseClaims(refreshToken);
        String memberId = claims.getSubject();
        Member member =  authMapper.selectByMemberId(Long.valueOf(memberId))
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        // 2. AccessToken 재발급하기
        JwtDTO jwtDTO = jwtTokenProvider.generateToken(member);

        if(jwtDTO==null)
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);

        return jwtDTO;
    }
}
