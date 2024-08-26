package com.innerpeace.themoonha.domain.lounge.dto;

import com.innerpeace.themoonha.global.util.DateTimeUtil;
import lombok.*;

/**
 * 라운지 목록 응답 DTO
 * @author 조희정
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	조희정       최초 생성
 * 2024.08.26  	조희정       DateTimeUtil 적용
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoungeListResponse {

    private Long loungeId;
    private String title;
    private String loungeImgUrl;
    private String latestPostTime;

    public void setLatestPostTime(String latestPostTime) {
        this.latestPostTime = DateTimeUtil.timeAgo(latestPostTime);
    }

}
