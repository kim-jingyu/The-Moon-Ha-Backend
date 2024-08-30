package com.innerpeace.themoonha.domain.admin.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 어드민 강좌 등록 요청 dto
 * @author 최유경
 * @since 2024.08.28
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.28  	최유경       최초 생성
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LessonRegisterAdminRequest {
    private Long branchId;
    private Long categoryId;
    private Long memberId;
    private String title;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String summary;
    private int cnt;
    private int cost;
    private String curriculum;
    private String supply;
    private String place;
    private String day;
    private int target;
    private int onlineCost;
}
