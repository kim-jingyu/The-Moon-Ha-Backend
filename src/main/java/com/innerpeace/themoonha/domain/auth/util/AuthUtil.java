package com.innerpeace.themoonha.domain.auth.util;

import javax.servlet.http.Cookie;

/**
 * 쿠키 생성 Util
 * @author 최유경
 * @since 2024.08.27
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  	최유경       최초 생성
 * 2024.09.13   최유경       쿠키 삭제 구현
 * </pre>
 */
public class AuthUtil {
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24; // 쿠키 유효 기간 (1일)
    private static final String COOKIE_PATH = "/"; // 쿠키가 유효한 경로

    /**
     * 쿠키 생성 메서드
     *
     * @param name Cookie key
     * @param token Cookie value
     * @return Cookie
     */
    public static Cookie createJwtTokenCookie(String name, String token) {
        Cookie cookie = new Cookie(name, token);
        cookie.setHttpOnly(true); // 클라이언트 측에서 자바스크립트로 쿠키를 접근하지 못하도록 설정
        cookie.setSecure(false); // cookie.setSecure(true); HTTPS 를 사용하는 경우만 쿠키를 전송하도록 설정
        cookie.setPath(COOKIE_PATH); // 쿠키의 유효 경로 설정
        cookie.setMaxAge(COOKIE_MAX_AGE); // 쿠키 유효 기간 설정
        return cookie;
    }

    /**
     * 쿠키 삭제 메서드
     *
     * @param key Cookie key
     * @return
     */
    public static Cookie createJwtTokenDeleteCookie(String key) {
        Cookie cookie = new Cookie(key, "");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(0);

        return cookie;
    }
}
