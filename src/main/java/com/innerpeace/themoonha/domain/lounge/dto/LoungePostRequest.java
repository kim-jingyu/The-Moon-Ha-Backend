package com.innerpeace.themoonha.domain.lounge.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 라운지 게시글 작성 Request
 * @author 조희정
 * @since 2024.08.27
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  	조희정       최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoungePostRequest {
    private Long loungePostId;
    private Long loungeId;
    private String content;
    private Boolean noticeYn;
}
