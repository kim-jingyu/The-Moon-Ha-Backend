package com.innerpeace.themoonha.global.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    CART_LESSON_ADDED_SUCCESS(200, "강좌가 성공적으로 장바구니에 담겼습니다"),
    SUGANG_APPLICATION_SUCCESS(200, "수강신청이 완료되었습니다"),
    LOUNGE_POST_ADD_SUCCESS(200, "라운지에 게시물이 등록되었습니다."),
    LOUNGE_COMMENT_ADD_SUCCESS(200, "라운지에 댓글이 등록되었습니다.");
    private final int status;
    private final String message;
}
