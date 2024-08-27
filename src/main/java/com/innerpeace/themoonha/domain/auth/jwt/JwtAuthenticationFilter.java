package com.innerpeace.themoonha.domain.auth.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * JWT 인가/인증을 위한 Filter
 * @author 최유경
 * @since 2024.08.25
 * @version 1.0
 * @apiNote API에 접근하기 전에, Request Header에 있는 JWT 토큰을 추출하여, 토큰의 내용을 검증, 인증 내용을 검증하는 필터
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	최유경       최초 생성
 * </pre>
 */

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * JWT 인증 필터
     *
     * @apiNote 요청 시 거치는 필터 로직
     * @param request ServletRequest
     * @param response ServletResponse
     * @param filterChain FilterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // 1. Request Header 에서 JWT 토큰 추출
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 2. validateToken 으로 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 Security Context 에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);

            log.info("JwtAuthenticationFilter : {}", authentication.getAuthorities());
            // 해당 요청에 대한 인증 성공 / 실패 및 사용자 정보를 포함한 인증 정보가 컨텍스트에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
