package com.innerpeace.themoonha.domain.auth.dto;

import com.innerpeace.themoonha.domain.auth.vo.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 회원가입 요청 dto
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
