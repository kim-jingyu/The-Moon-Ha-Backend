package com.innerpeace.themoonha.domain.auth.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    ADMIN(0,"ADMIN"),
    MEMBER(1,"MEMBER"),
    TUTOR(2,"TUTOR");

    private final int num;
    private final String role;

    public static MemberRole fromNum(int num) {
        for (MemberRole role : values()) {
            if (role.getNum() == num) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid MemberRole number: " + num);
    }
}
