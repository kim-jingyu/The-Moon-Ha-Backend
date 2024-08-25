package com.innerpeace.themoonha.domain.auth.dto;

import com.innerpeace.themoonha.domain.auth.vo.MemberRole;
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
public class SignUpRequest {
    private String username;
    private String password;
    private String name;
    private MemberRole memberRole;
}
