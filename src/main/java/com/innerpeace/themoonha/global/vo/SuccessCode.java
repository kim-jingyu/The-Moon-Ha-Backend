package com.innerpeace.themoonha.global.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    CART_LESSON_ADDED_SUCCESS(200, "강좌가 성고적으로 장바구니에 담겼습니다");
    private final int status;
    private final String message;
}
