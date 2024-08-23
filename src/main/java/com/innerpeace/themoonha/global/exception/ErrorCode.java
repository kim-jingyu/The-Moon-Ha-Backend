package com.innerpeace.themoonha.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(404, "존재하지 않은 유저입니다.");
    private final int status;
    private final String message;

}
