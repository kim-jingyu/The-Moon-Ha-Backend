package com.innerpeace.themoonha.global.xss;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * XSS 처리 필터 래퍼 클래스
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
public class XSSFilterWrapper extends HttpServletRequestWrapper {

    private byte[] rawData;

    public XSSFilterWrapper(HttpServletRequest request) {
        super(request);
        try {
            if (request.getMethod().equalsIgnoreCase("post") && (request.getContentType().equals("application/json") || request.getContentType().equals("multipart/form-data"))) {
                InputStream is = request.getInputStream();
                this.rawData = replaceXSS(IOUtils.toByteArray(is));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] replaceXSS(byte[] data) {
        String strData = new String(data);
        strData = strData.replaceAll("\\<", "&lt;").replaceAll("\\>", "&gt;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");

        return strData.getBytes();
    }

    private String replaceXSS(String value) {
        if (value != null) {
            value = value.replaceAll("\\<", "&lt;").replaceAll("\\>", "&gt;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        }
        return value;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (this.rawData == null) {
            return super.getInputStream();
        }
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }

    @Override
    public String getQueryString() {
        return replaceXSS(super.getQueryString());
    }


    @Override
    public String getParameter(String name) {
        return replaceXSS(super.getParameter(name));
    }


    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> params = super.getParameterMap();
        if (params != null) {
            params.forEach((key, value) -> {
                for (int i = 0; i < value.length; i++) {
                    value[i] = replaceXSS(value[i]);
                }
            });
        }
        return params;
    }


    @Override
    public String[] getParameterValues(String name) {
        String[] params = super.getParameterValues(name);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                params[i] = replaceXSS(params[i]);
            }
        }
        return params;
    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), StandardCharsets.UTF_8));
    }
}



