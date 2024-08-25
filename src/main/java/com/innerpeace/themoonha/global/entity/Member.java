package com.innerpeace.themoonha.global.entity;

import com.innerpeace.themoonha.domain.auth.dto.SignUpRequest;
import com.innerpeace.themoonha.domain.auth.vo.MemberRole;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member {
    private int memberId;
    private String username;
    private String password;
    private String name;
    private MemberRole memberRole;
    private String profileImgUrl;
    private String fcmToken;
    private Date createdAt;
    private Date deletedAt;

    public static Member of(SignUpRequest request, String password){
        return Member.builder()
                .username(request.getUsername())
                .password(password)
                .name(request.getName())
                .memberRole(request.getMemberRole())
                .build();
    }

    public void setMemberRole(int memberRoleNum) {
        this.memberRole = MemberRole.fromNum(memberRoleNum);
    }
}
