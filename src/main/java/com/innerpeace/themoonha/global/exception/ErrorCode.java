package com.innerpeace.themoonha.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(404, "존재하지 않은 유저입니다."),
    LESSON_NOT_FOUND(404, "강좌가 존재하지 않습니다."),
    SHORTFORM_NOT_FOUND(404, "숏폼이 존재하지 않습니다."),
    MEMBER_DUPLICATE(409, "중복되는 유저가 존재합니다. 다시 시도해주세요."),
    TUTOR_NOT_FOUND(404, "강사가 존재하지 않습니다"),
    CART_LESSON_ALREADY_EXISTS(409, "강좌가 이미 장바구니에 담겨있습니다."),
    LOUNGE_NOT_FOUND(404, "라운지 정보가 존재하지 않습니다.");
    private final int status;
    private final String message;

}
