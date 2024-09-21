package com.innerpeace.themoonha.global.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * XSS 처리 필터
 *
 * @author 손승완
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.12  	손승완       최초 생성
 * </pre>
 * @since 2024.09.12
 */
public class XSSFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequestWrapper requestWrapper = new XSSFilterWrapper((HttpServletRequest)request);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
