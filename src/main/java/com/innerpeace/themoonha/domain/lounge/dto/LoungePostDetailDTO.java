package com.innerpeace.themoonha.domain.lounge.dto;


import lombok.*;

import java.util.List;

/**
 * 라운지 게시글 상세정보 DTO
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
public class LoungePostDetailDTO {

    private LoungePostDTO loungePost;
    private List<LoungeCommentDTO> loungeCommentList;

    public static LoungePostDetailDTO of(LoungePostDTO loungePost, List<LoungeCommentDTO> loungeCommentList) {
        return LoungePostDetailDTO.builder()
                .loungePost(loungePost)
                .loungeCommentList(loungeCommentList)
                .build();
    }
}
