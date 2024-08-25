package com.innerpeace.themoonha.domain.auth.service;

import com.innerpeace.themoonha.domain.auth.dto.SignUpRequest;
import com.innerpeace.themoonha.domain.auth.mapper.AuthMapper;
import com.innerpeace.themoonha.global.entity.Member;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
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
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final AuthMapper authMapper;
    private final PasswordEncoder encoder;

    @Override
    public int signUp(SignUpRequest request) {
        if(!checkAvailableUsername(request.getUsername()))
            throw new CustomException(ErrorCode.MEMBER_DUPLICATE);

        // 비밀번호 암호화
        String encodedPassword = encoder.encode(request.getPassword());

        Member member = Member.of(request,encodedPassword);

        int result = authMapper.insertMember(member);

        return result;
    }

    @Override
    public boolean checkAvailableUsername(String userName) {
        return !authMapper.selectMemberByUsername(userName).isPresent();
    }
}
