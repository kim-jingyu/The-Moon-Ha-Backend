package com.innerpeace.themoonha.global.exception;

import lombok.Getter;

@Getter
public class JsonTypeConvertException extends RuntimeException {
    private final ErrorCode errorCode;

    public JsonTypeConvertException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
