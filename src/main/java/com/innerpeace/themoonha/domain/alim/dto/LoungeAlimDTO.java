package com.innerpeace.themoonha.domain.alim.dto;

import lombok.*;

/**
 * 알림 전송 DTO
 * @author 조희정
 * @since 2024.09.11
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.11  	조희정       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoungeAlimDTO {
    private String fcmToken;
    private String lessonTitle;
}
