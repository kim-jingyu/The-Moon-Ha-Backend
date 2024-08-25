package com.innerpeace.themoonha.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
