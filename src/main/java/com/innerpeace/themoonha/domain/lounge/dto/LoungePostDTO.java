package com.innerpeace.themoonha.domain.lounge.dto;

import com.innerpeace.themoonha.global.util.DateTimeUtil;
import lombok.*;

import java.util.ArrayList;
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


    public static LoungePostDTO of(LoungePostDTO loungePostDTO, List<String> loungePostImgList) {
        return LoungePostDTO.builder()
                .loungePostId(loungePostDTO.getLoungePostId())
                .content(loungePostDTO.getContent())
                .noticeYn(loungePostDTO.isNoticeYn())
                .loungePostImgList(loungePostImgList != null ? loungePostImgList : new ArrayList<>())
                .createdAt(DateTimeUtil.timeAgo(loungePostDTO.getCreatedAt()))
                .loungeMember(loungePostDTO.getLoungeMember())
                .build();
    }


}
