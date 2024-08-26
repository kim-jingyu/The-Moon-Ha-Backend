package com.innerpeace.themoonha.domain.lounge.dto;

import com.innerpeace.themoonha.global.util.DateTimeUtil;
import lombok.*;

import java.util.List;

/**
 * 라운지 홈 게시글 정보 DTO
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
public class LoungePostDTO {

    // 게시글 정보
    private Long loungePostId;
    private String content;
    private boolean noticeYn;
    private List<String> loungePostImgList;
    private String createdAt;

    // 작성자 정보
    private LoungeMemberDTO loungeMember;

    public void setCreatedAt(String createdAt) {
        this.createdAt = DateTimeUtil.timeAgo(createdAt);
    }

}
