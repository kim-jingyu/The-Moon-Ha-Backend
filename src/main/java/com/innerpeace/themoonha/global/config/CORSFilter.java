package com.innerpeace.themoonha.global.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept, Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
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
