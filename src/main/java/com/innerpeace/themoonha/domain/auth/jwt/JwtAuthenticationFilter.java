package com.innerpeace.themoonha.domain.auth.jwt;

import com.innerpeace.themoonha.domain.auth.dto.JwtDTO;
import com.innerpeace.themoonha.domain.auth.service.AuthService;
import com.innerpeace.themoonha.domain.auth.util.AuthUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
 * 2024.08.27   최유경       만료된 토큰 재발급 로직
 * 2024.09.06   최유경       MemberId 어노테이션 추가
 * </pre>
 */

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

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
        log.info("[][] JwtAuthenticationFilter - Before setting memberId");
        JwtDTO jwtDTO = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String accessToken = jwtDTO.getAccessToken();

        try{
            // 2. validateToken 으로 토큰 유효성 검사
            if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
                // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 Security Context 에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

                log.info("[][] JwtAuthenticationFilter : {}", authentication.getAuthorities());
                // 해당 요청에 대한 인증 성공 / 실패 및 사용자 정보를 포함한 인증 정보를 컨텍스트에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);

                request.setAttribute("memberId", Long.parseLong(authentication.getName()));

                log.info("[][] JwtAuthenticationFilter - memberId set to: {}", authentication.getName());

                filterChain.doFilter(request,response);
            }
        } catch (Exception e){ // accessToken, refreshToken 재발급
            log.info("만료만료만료");
            String refreshToken = jwtDTO.getRefreshToken();
            if(refreshToken != null && jwtTokenProvider.validateToken(refreshToken)){
                JwtDTO reDTO = authService.regenerateToken(refreshToken);

                // 새로운 AccessToken 을 헤더에 추가
//                ((HttpServletResponse) response).setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + reDTO.getAccessToken());
                Cookie accessTokenCookie = AuthUtil.createJwtTokenCookie("accessToken", reDTO.getAccessToken());

                // RefreshToken Cookie 추가
                Cookie refreshTokenCookie = AuthUtil.createJwtTokenCookie("refreshToken", reDTO.getRefreshToken());
                ((HttpServletResponse) response).addCookie(accessTokenCookie);
                ((HttpServletResponse) response).addCookie(refreshTokenCookie);

                // Security Context 에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(reDTO.getAccessToken());

                log.info("[][] JwtAuthenticationFilter : {}", authentication.getAuthorities());
                // 인증 정보를 컨텍스트에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);

                request.setAttribute("memberId", Long.parseLong(authentication.getName()));

                log.info("[][] JwtAuthenticationFilter - memberId set to: {}", authentication.getName());

                filterChain.doFilter(request,response);
            }
        }
    }
}
