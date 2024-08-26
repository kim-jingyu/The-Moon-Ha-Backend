package com.innerpeace.themoonha.domain.lounge.dto;

import com.innerpeace.themoonha.global.util.DateTimeUtil;
import lombok.*;

/**
 * 라운지 게시글 댓글 DTO
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
public class LoungeCommentDTO {
    private Long loungeCommentId;
    private String content;
    private String createdAt;
    private LoungeMemberDTO loungeCommentMember;

    public void setCreatedAt(String createdAt) {
        this.createdAt = DateTimeUtil.timeAgo(createdAt);
    }
}
