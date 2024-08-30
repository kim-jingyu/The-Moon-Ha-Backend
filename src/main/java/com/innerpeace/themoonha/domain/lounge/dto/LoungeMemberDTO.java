package com.innerpeace.themoonha.domain.lounge.dto;

import lombok.*;

/**
 * 라운지 회원 목록 DTO
 * @author 조희정
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	조희정       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoungeMemberDTO {
    private Long memberId;
    private String name;
    private String profileImgUrl;
}
