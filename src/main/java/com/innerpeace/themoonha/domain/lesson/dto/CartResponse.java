package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



/**
 * 장바구니 응답 DTO
 * @author 손승완
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26   손승완       최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartResponse {
    private String branchName;
    private String cartId;
    private String lessonTitle;
    private String period;
    private String lessonTime;
    private String tutorName;
    private String target;
    private int cost;
    private Integer onlineCost;
    private boolean onlineYn;
}
