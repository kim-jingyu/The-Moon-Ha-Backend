package com.innerpeace.themoonha.global.config;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

/**
 * CORS 필터 설정
 * @author 최유경
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	최유경       최초 생성
 * </pre>
 */
@Configuration
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 허용할 출처 목록
        String[] allowedOrigins = {"http://localhost:3000", "https://web.themoonha.site", "https://themoonha.site", "http://themoonha.site"};
        String origin = request.getHeader("Origin");

        // 요청 출처가 허용된 출처 목록에 포함되어 있는지 확인
        boolean isAllowed = Arrays.asList(allowedOrigins).contains(origin);

        if (isAllowed) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            response.setHeader("Access-Control-Allow-Origin", ""); // 허용되지 않은 출처의 경우 빈 문자열로 설정
        }
//        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept, Authorization");
//        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }

    /**
     * 필터 초기화 메서드
     * @param filterConfig 필터 설정 객체
     */
    @Override
    public void init(FilterConfig filterConfig) {}

    /**
     * 필터 종료 메서드
     */
    @Override
    public void destroy() {}
}
