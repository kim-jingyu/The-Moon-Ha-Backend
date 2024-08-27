package com.innerpeace.themoonha.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * JWT accessToken, refreshToken 담는 DTO
 * @author 최유경
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	최유경       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@ToString
public class JwtDTO {
    private String accessToken;
    private String refreshToken;

    public static JwtDTO of(String accessToken, String refreshToken){
        return JwtDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
