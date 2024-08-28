package com.innerpeace.themoonha.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 공통 응답 dto
 * @author 최유경
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	최유경       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
public class CommonResponse {
    private boolean success;
    private String message;

    public static CommonResponse from(String message){
        return CommonResponse.builder()
                .success(true)
                .message(message)
                .build();
    }

    public static CommonResponse of(boolean success, String message){
        return CommonResponse.builder()
                .success(success)
                .message(message)
                .build();
    }
}
