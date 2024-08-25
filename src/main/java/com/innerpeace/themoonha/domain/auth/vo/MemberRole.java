package com.innerpeace.themoonha.domain.auth.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 회원 역할 enum
 * @author 최유경
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	최유경       최초 생성
 * </pre>
 */
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
